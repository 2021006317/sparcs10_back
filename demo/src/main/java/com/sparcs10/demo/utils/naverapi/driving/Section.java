package com.sparcs10.demo.utils.naverapi.driving;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Section {
    private int pointIndex;
    private int pointCount;
    private int distance;
    private String name;
    private int congestion;
    private int speed;
}
