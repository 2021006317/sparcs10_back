package com.sparcs10.demo.utils.naverapi.geotocord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CordRoot {
    private String status;
    private Meta meta;
    private Address[] addresses;
    private String errorMessage;

}