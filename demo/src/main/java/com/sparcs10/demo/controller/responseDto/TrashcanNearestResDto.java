package com.sparcs10.demo.controller.responseDto;

import com.sparcs10.demo.dto.TrashcanDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class TrashcanNearestResDto {
    private String address;
    private List<String> types;
    private Double latitude;
    private Double longitude;
    private Long distance;
    private Long duration;

    public TrashcanNearestResDto(TrashcanDTO dto, double latitude, double longitude) {
        this.address = dto.getAddress();
        this.types = dto.getTypes();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public TrashcanNearestResDto(TrashcanDTO dto, double latitude, double longitude, Long distance, Long duration) {
        this.address = dto.getAddress();
        this.types = dto.getTypes();
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.duration = duration;
    }
}
