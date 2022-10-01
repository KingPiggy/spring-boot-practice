package com.kingpiggy.study.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    // api 통신시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String code;

    // api 부가 설명
    private String message;

    private T data;

    // OK
    public static <T> ApiResponse<T> OK(){
        return (ApiResponse<T>)ApiResponse.builder()
                .transactionTime(LocalDateTime.now())
                .code("OK")
                .message("OK")
                .build();
    }


    // DATA OK
    public static <T> ApiResponse<T> OK(T data){
        return (ApiResponse<T>)ApiResponse.builder()
                .transactionTime(LocalDateTime.now())
                .code("OK")
                .message("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> ApiResponse<T> ERROR(String description){
        return (ApiResponse<T>)ApiResponse.builder()
                .transactionTime(LocalDateTime.now())
                .code("ERROR")
                .message(description)
                .build();
    }

    // ERROR with Code
    public static <T> ApiResponse<T> ERROR(String code, String description){
        return (ApiResponse<T>)ApiResponse.builder()
                .transactionTime(LocalDateTime.now())
                .code(code)
                .message(description)
                .build();
    }
    // ERROR with Code
    public static <T> ApiResponse<T> ERROR(String code, String description, T data){
        return (ApiResponse<T>)ApiResponse.builder()
                .transactionTime(LocalDateTime.now())
                .code(code)
                .message(description)
                .data(data)
                .build();
    }
}
