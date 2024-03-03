package com.project.bookmyshow.commands.allCommands;

import com.project.bookmyshow.commands.Command;
import com.project.bookmyshow.commands.CommandKeyword;
import com.project.bookmyshow.controllers.UserController;
import com.project.bookmyshow.dtos.requestDto.SignUpUserRequestDto;
import com.project.bookmyshow.dtos.requestDto.UpdateUserInfoRequestDto;
import com.project.bookmyshow.dtos.responseDto.UpdateUserInfoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class UpdateUserProfileCommand implements Command {

    private UserController userController;
    private Scanner scanner;

    @Autowired
    public UpdateUserProfileCommand(UserController userController) {

        this.userController = userController;
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean macthes(int input) {
        if (input == CommandKeyword.UPDATE_USER) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        UpdateUserInfoRequestDto request = new UpdateUserInfoRequestDto();
        System.out.println("Enter the email id : ");
        String email = scanner.next();
        request.setUserEmail(email);
        System.out.println("Enter your name : ");
        String name = scanner.next();
        request.setUserName(name);
        System.out.println("Enter your age : ");
        int age = scanner.nextInt();
        request.setUserAge(age);
        System.out.println("Enter your phone number : ");
        String phNo = scanner.next();
        request.setUserphoneNo(phNo);
        UpdateUserInfoResponseDto response = userController.updateUserInfo(request);
        System.out.println(response.getMessage());
    }
}
