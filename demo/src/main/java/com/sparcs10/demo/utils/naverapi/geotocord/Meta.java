package com.sparcs10.demo.utils.naverapi.geotocord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
    private int totalCount;
    private int page;
    private int count;
}