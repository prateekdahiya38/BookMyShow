package com.project.bookmyshow.dtos.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserInfoResponseDto {
    private Long userId;
    private String UserName;
    private String email;
    private String phoneNo;
    private String responseStatus;
    private String message;
    private int age;
}
