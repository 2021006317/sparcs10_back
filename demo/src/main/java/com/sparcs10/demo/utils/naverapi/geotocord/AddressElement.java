package com.sparcs10.demo.utils.naverapi.geotocord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressElement {
    private String[] types;
    private String longName;
    private String shortName;
    private String code;
}