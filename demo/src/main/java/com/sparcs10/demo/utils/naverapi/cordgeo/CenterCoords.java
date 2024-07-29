package com.sparcs10.demo.utils.naverapi.cordgeo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CenterCoords {
    private String crs;
    private double x;
    private double y;

}
