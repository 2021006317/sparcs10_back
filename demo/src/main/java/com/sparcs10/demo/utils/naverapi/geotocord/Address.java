package com.sparcs10.demo.utils.naverapi.geotocord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private String roadAddress;
    private String jibunAddress;
    private String englishAddress;
    private AddressElement[] addressElements;
    private String x;
    private String y;
    private double distance;

}
