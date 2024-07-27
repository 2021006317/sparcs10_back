package com.sparcs10.demo.utils.naverapi.driving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {
    private Start start;
    private Goal goal;
    private int distance;
    private long duration;
    private List<List<Double>> bbox;
    private int tollFare;
    private int taxiFare;
    private int fuelPrice;


    public static class Start {
        private List<Double> location;

    }

    public static class Goal {
        private List<Double> location;
        private int dir;

    }
}
