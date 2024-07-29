package com.sparcs10.demo.controller.requestDto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public class TrashcanLatlngRequest {
    private Double latitude;
    private Double longitude;
}
