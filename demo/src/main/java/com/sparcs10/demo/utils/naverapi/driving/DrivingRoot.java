package com.sparcs10.demo.utils.naverapi.driving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrivingRoot {
    private int code;
    private String message;
    private String currentDateTime;
    private Route route;


    @Getter
    @Setter
    public static class Route {
        private List<Trafast> trafast;
    }
}