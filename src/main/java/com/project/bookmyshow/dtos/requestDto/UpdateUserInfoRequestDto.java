package com.project.bookmyshow.dtos.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserInfoRequestDto {
    private String userName;
    private String userEmail;
    private int userAge;
    private String UserphoneNo;
}
