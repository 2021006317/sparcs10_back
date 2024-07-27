package com.sparcs10.demo.controller.requestDto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
public class TrashcanCreateRequest {
    private String address;
    private String types;

}
