package com.sparcs10.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparcs10.demo.controller.requestDto.TrashcanCreateRequest;
import com.sparcs10.demo.dto.TrashcanDTO;
import com.sparcs10.demo.entity.Trashcan;
import com.sparcs10.demo.repository.TrashcanRepository;
import com.sparcs10.demo.utils.naverapi.driving.DrivingRoot;
import com.sparcs10.demo.utils.naverapi.cordgeo.GeoRoot;
import com.sparcs10.demo.utils.naverapi.geotocord.CordRoot;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashcanService {
    private final TrashcanRepository trashcanRepository;

    @Value("${spring.naver.client-id}")
    private String NAVER_CLEINT_ID;

    @Value("${spring.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    public List<TrashcanDTO> list() {
        return trashcanRepository.findAll().stream()
                .map(TrashcanDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public TrashcanDTO create(TrashcanCreateRequest request) {
        List<String> types = List.of(request.getTypes().split("-"));
        Trashcan trashcan = Trashcan.builder()
                .address(request.getAddress())
                .types(String.join(",", types))
                .build();
        trashcanRepository.save(trashcan);
        return TrashcanDTO.fromEntity(trashcan);
    }

    public TrashcanDTO nearestTrashcan(double currentLatitude, double currentLongitude) { // 위도, 경도
        /*
        * 1. 주어진 위도, 경도를 주소로 변환한다.
        * 2. 주소를 기반으로 같은 시, 구에 있는 쓰레기통을 추린다.
        * 3. 주어진 위도, 경도와 쓰레기통의 위도, 경도를 비교하여 가장 가까운 쓰레기통을 찾는다.
        * 4. 소요 시간이 길어지는 것을 방지하기 위해 쓰레기통의 위도, 경도를 기반으로 1km 이내에 있는 쓰레기통을 추린다.
        * 5. 가장 가까운 쓰레기통을 반환한다.
        * 6. 만약 쓰레기통이 없다면 null을 반환한다.
         */
        String address = convertCordToGeo(currentLatitude, currentLongitude);
        String[] addressArr = address.split(" "); // 이게 공백으로 나눠지는게 맞는지 확인해야함.
        String city = addressArr[0];
        String district = addressArr[1];
        List<TrashcanDTO> trashcanList = null;

        trashcanList = trashcanRepository.findByAddressContainingAndAddressContaining(city, district).stream()
                .map(TrashcanDTO::fromEntity)
                .collect(Collectors.toList());
        if (trashcanList.isEmpty()) {
            trashcanList = trashcanRepository.findByAddressContaining(city).stream()
                    .map(TrashcanDTO::fromEntity)
                    .collect(Collectors.toList());
        } if (trashcanList.isEmpty()) {
            return null;
        }


        // 임의로 잡아준 것.
        TrashcanDTO nearestTrashcan = trashcanList.get(0);

        List<String> nearestCord = convertGeoToCord(nearestTrashcan.getAddress());
        String nearestLatitude = nearestCord.get(0);
        String nearestLongitude = nearestCord.get(1);
        long minDuration = getDrivingDuration(currentLatitude, currentLongitude, Double.parseDouble(nearestLatitude), Double.parseDouble(nearestLongitude));

        // 거리 계산
        for (TrashcanDTO trashcan : trashcanList) {
            List<String> cord = convertGeoToCord(trashcan.getAddress());
            String trashcanLatitude = cord.get(0);
            String trashcanLongitude = cord.get(1);
            long duration = getDrivingDuration(currentLatitude, currentLongitude, Double.parseDouble(trashcanLatitude), Double.parseDouble(trashcanLongitude));
            if (duration < minDuration){
                minDuration = duration;
                nearestTrashcan = trashcan;
            }
        }

        return nearestTrashcan;
    }

    public List<String> convertGeoToCord(String address) {
        // curl -G "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode" \
        //    --data-urlencode "query={주소}" \
        //    --data-urlencode "coordinate={검색_중심_좌표}" \
        //    -H "X-NCP-APIGW-API-KEY-ID: {애플리케이션 등록 시 발급받은 client id값}" \
        //    -H "X-NCP-APIGW-API-KEY: {애플리케이션 등록 시 발급받은 client secret값}" -v

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("naveropenapi.apigw.ntruss.com")
                .path("/map-geocode/v2/geocode")
                .queryParam("query", address)
                .build().toUri();

        RequestEntity<Void> req = RequestEntity.
                get(uri)
                .header("X-NCP-APIGW-API-KEY-ID", NAVER_CLEINT_ID)
                .header("X-NCP-APIGW-API-KEY", NAVER_CLIENT_SECRET)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        String convertResult = result.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        CordRoot cordRoot = null;
        try {
            cordRoot = objectMapper.readValue(convertResult, CordRoot.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String latitude = cordRoot.getAddresses()[0].getY();
        String longitude = cordRoot.getAddresses()[0].getX();

        return List.of(latitude, longitude);

    }

    public String convertCordToGeo(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("naveropenapi.apigw.ntruss.com")
                .path("/map-reversegeocode/v2/gc")
                .queryParam("coords", longitude + "," + latitude) // 경도, 위도 순
                .queryParam("orders", "addr,roadaddr")
                .queryParam("output", "json")
                .build().toUri();

        RequestEntity<Void> req = RequestEntity.
                get(uri)
                .header("X-NCP-APIGW-API-KEY-ID", NAVER_CLEINT_ID)
                .header("X-NCP-APIGW-API-KEY", NAVER_CLIENT_SECRET)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        String convertResult = result.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        GeoRoot geoRoot = null;
        try{
            geoRoot = objectMapper.readValue(convertResult, GeoRoot.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String address = geoRoot.getResults()[0].getRegion().getArea1().getName() + " " +
                geoRoot.getResults()[0].getRegion().getArea2().getName() + " " +
                geoRoot.getResults()[0].getRegion().getArea3().getName();

        return address;
    }

    public long getDrivingDuration(double startLatitude, double startLongitude, double goalLatitude, double goalLongitude) {
        RestTemplate restTemplate = new RestTemplate();

        // curl "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start={출발지}&goal={목적지}&option={탐색옵션}" \
        //	-H "X-NCP-APIGW-API-KEY-ID: {애플리케이션 등록 시 발급받은 client id 값}" \
        //	-H "X-NCP-APIGW-API-KEY: {애플리케이션 등록 시 발급받은 client secret값}" -v

        URI uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("naveropenapi.apigw.ntruss.com")
                .path("/map-direction/v1/driving")
                .queryParam("start", startLongitude + "," + startLatitude)
                .queryParam("goal", goalLongitude + "," + goalLatitude)
                .queryParam("option", "trafast")
                .build().toUri();

        RequestEntity<Void> req = RequestEntity.
                get(uri)
                .header("X-NCP-APIGW-API-KEY-ID", NAVER_CLEINT_ID)
                .header("X-NCP-APIGW-API-KEY", NAVER_CLIENT_SECRET)
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        String convertResult = result.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        DrivingRoot root = null;
        try{
            root = objectMapper.readValue(convertResult, DrivingRoot.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long duration = root.getRoute().getTrafast().get(0).getSummary().getDuration();

        return duration;
    }
}
