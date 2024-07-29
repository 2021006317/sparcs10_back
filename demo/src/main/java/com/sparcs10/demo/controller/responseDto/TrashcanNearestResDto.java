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
@AllArgsConstructor
public class TrashcanNearestResDto {
    private String address;
    private List<String> types;
    private double latitude;
    private double longitude;

    public TrashcanNearestResDto(TrashcanDTO dto, double latitude, double longitude) {
        this.address = dto.getAddress();
        this.types = dto.getTypes();
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
