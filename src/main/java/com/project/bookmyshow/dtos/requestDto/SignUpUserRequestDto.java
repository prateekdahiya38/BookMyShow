package com.project.bookmyshow.dtos.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserRequestDto {
    private String email;
    private String password;
}
