package com.sparcs10.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparcs10.demo.entity.Trashcan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrashcanDTO {
    private String id;
    private String address;
    private List<String> types;
    private Double latitude;
    private Double longitude;

    public static TrashcanDTO fromEntity(Trashcan entity) {
        TrashcanDTO dto = new TrashcanDTO();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        if (entity.getTypes() != null)
            dto.setTypes(List.of(entity.getTypes().split(",")));
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        return dto;
    }
}
