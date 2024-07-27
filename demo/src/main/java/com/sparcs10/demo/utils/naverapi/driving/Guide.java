package com.sparcs10.demo.utils.naverapi.driving;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guide {
    private int pointIndex;
    private int type;
    private String instructions;
    private int distance;
    private int duration;
}
