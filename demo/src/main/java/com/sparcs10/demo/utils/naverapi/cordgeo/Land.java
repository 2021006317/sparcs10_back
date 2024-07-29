package com.sparcs10.demo.utils.naverapi.cordgeo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Land {
    private String type;
    private String number1;
    private String number2;
    private Addition addition0;
    private Addition addition1;
    private Addition addition2;
    private Addition addition3;
    private Addition addition4;
    private String name;
    private CenterCoords coords;
}
