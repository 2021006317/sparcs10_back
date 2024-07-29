package com.sparcs10.demo.utils.naverapi.driving;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {
    private int distance;
    private long duration;

}
