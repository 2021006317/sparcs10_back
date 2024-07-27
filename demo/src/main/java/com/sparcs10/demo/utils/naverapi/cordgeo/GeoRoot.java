package com.sparcs10.demo.utils.naverapi.cordgeo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoRoot {
    private Status status;
    private Result[] results;

}