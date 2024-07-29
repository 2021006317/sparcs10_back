package com.sparcs10.demo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({"statusCode", "message", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CustomResponse<T>{
    // ResponseCode 로 관리
    private HttpStatusCode statusCode;
    private String message;
    private T data;

    public CustomResponse(HttpStatusCode statusCode, T data){
        this.statusCode = statusCode;
        this.data = data;
    }

    public static <T> CustomResponse<T> okresponse(T data) {
        return new CustomResponse<>(HttpStatus.OK, data);
    }

    public static <T> CustomResponse<T> okresponse(HttpStatus status, T data) {
        return new CustomResponse<>(
                HttpStatusCode.valueOf(status.value()),
                data
        );
    }
}
