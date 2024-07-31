package com.sparcs10.demo.controller;

import com.sparcs10.demo.controller.requestDto.CreateLatlngReq;
import com.sparcs10.demo.controller.requestDto.TrashcanCreateRequest;
import com.sparcs10.demo.controller.responseDto.TrashcanNearestResDto;
import com.sparcs10.demo.dto.TrashcanDTO;
import com.sparcs10.demo.service.TrashcanService;
import com.sparcs10.demo.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trashcan")
@RequiredArgsConstructor
public class TrashcanController {
    private final TrashcanService trashcanService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<TrashcanDTO>>> list(@RequestParam(required = false) Double currentLatitude, @RequestParam(required = false) Double currentLongitude) {
        if (currentLatitude != null && currentLongitude != null) {
            return new ResponseEntity<>(
                    CustomResponse.response(trashcanService.list(currentLatitude, currentLongitude)),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                CustomResponse.response(trashcanService.list(null, null)),
                HttpStatus.OK
        );
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<TrashcanDTO>> create(@RequestBody @Validated TrashcanCreateRequest request) {
        return new ResponseEntity<>(
                CustomResponse.response(HttpStatus.CREATED, trashcanService.create(request)),
                HttpStatus.CREATED
        );
    }


    @PostMapping("/create/latlng")
    public ResponseEntity<CustomResponse<TrashcanDTO>> create(@RequestBody @Validated CreateLatlngReq request) {
        return new ResponseEntity<>(
                CustomResponse.response(HttpStatus.CREATED, trashcanService.createLatlng(request)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/nearest")
    public ResponseEntity<CustomResponse<List<TrashcanNearestResDto>>> nearestTrashcan(@RequestParam Double currentLatitude, @RequestParam Double currentLongitude) {
        List<TrashcanNearestResDto> resDto = trashcanService.nearestTrashcan(currentLatitude, currentLongitude);
        if (resDto == null) {
            return new ResponseEntity<>(
                    CustomResponse.response(HttpStatus.NOT_FOUND, null),
                    HttpStatus.NOT_FOUND
            );
        } else {
            return new ResponseEntity<>(
                    CustomResponse.response(resDto),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping("/convert/address/to/cord")
    public ResponseEntity<CustomResponse<List<Double>>> convertAddressToCord(@RequestParam String address) {
        List<Double> cord = trashcanService.convertGeoToCord(address);
        if (cord == null) {
            return new ResponseEntity<>(
                    CustomResponse.response(HttpStatus.NO_CONTENT, null),
                    HttpStatus.NO_CONTENT
            );
        } else {
            return new ResponseEntity<>(
                    CustomResponse.response(cord),
                    HttpStatus.OK
            );
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CustomResponse<TrashcanDTO>> updateLatlng(@RequestParam String trashcanId) {
        return new ResponseEntity<>(
                CustomResponse.response(trashcanService.updateLatlng(trashcanId)),
                HttpStatus.OK
        );
    }

//    @GetMapping("/convert/cord/to/address")
//    public ResponseEntity<CustomResponse<String>> convertCordToAddress(@RequestParam double latitude, @RequestParam double longitude) {
//        String address = trashcanService.convertCordToGeo(latitude, longitude);
//        if (address == null) {
//            return new ResponseEntity<>(
//                    CustomResponse.okresponse(HttpStatus.NO_CONTENT, null),
//                    HttpStatus.NO_CONTENT
//            );
//        } else {
//            return new ResponseEntity<>(
//                    CustomResponse.okresponse(address),
//                    HttpStatus.OK
//            );
//        }
//    }
}
