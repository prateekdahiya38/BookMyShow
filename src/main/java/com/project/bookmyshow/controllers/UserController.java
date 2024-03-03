package com.project.bookmyshow.controllers;

import com.project.bookmyshow.dtos.requestDto.SignUpUserRequestDto;
import com.project.bookmyshow.dtos.requestDto.UpdateUserInfoRequestDto;
import com.project.bookmyshow.dtos.responseDto.SignUpUserResponseDto;
import com.project.bookmyshow.dtos.responseDto.UpdateUserInfoResponseDto;
import com.project.bookmyshow.models.User;
import com.project.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto request) {
        SignUpUserResponseDto response = new SignUpUserResponseDto();
        try{
        User user = userService.signUpUser(request.getEmail(), request.getPassword());
        response.setUserId(user.getId());
        response.setResponseStatus("SUCCESS");
        response.setMessage("User is siged up successfully");
        }catch (Exception e) {
            response.setResponseStatus("FAILURE");
            response.setMessage("The User is already exist!");
        }
        return response;
    }



    public UpdateUserInfoResponseDto updateUserInfo(UpdateUserInfoRequestDto request){
        UpdateUserInfoResponseDto response = new UpdateUserInfoResponseDto();
        try{
            User user = userService.udpateUser(request.getUserEmail(),request.getUserName(),request.getUserAge(),request.getUserphoneNo());
            response.setResponseStatus("SUCCESS");
            response.setMessage(user.getName() + " profile updated successfully");
        }catch (Exception e){
            response.setResponseStatus("FAILURE");
            response.setMessage(request.getUserName() + " as a user,does not exist");
        }
        return response;
    }
}
