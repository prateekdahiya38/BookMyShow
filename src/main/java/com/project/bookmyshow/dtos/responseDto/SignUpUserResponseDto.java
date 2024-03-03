package com.project.bookmyshow.dtos.responseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserResponseDto {
    private Long userId;
    private String responseStatus;
    private String message;
}
