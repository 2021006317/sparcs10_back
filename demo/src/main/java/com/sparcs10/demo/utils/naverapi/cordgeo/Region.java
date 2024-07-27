package com.sparcs10.demo.utils.naverapi.cordgeo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
    private Area area0;
    private Area area1;
    private Area area2;
    private Area area3;
    private Area area4;

}
