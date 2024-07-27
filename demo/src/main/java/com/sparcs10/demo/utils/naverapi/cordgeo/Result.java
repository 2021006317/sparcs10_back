package com.sparcs10.demo.utils.naverapi.cordgeo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String name;
    private Code code;
    private Region region;

}