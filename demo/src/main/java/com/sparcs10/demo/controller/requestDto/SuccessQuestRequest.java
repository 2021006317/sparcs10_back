package com.sparcs10.demo.controller.requestDto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize
@ToString
public class SuccessQuestRequest {
    private String questContent;
    private String userName;
    private LocalDate successDate;
}
