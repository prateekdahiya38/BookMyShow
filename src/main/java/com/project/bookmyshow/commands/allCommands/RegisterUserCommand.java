package com.project.bookmyshow.commands.allCommands;

import com.project.bookmyshow.commands.Command;
import com.project.bookmyshow.commands.CommandKeyword;
import com.project.bookmyshow.controllers.UserController;
import com.project.bookmyshow.dtos.requestDto.SignUpUserRequestDto;
import com.project.bookmyshow.dtos.responseDto.SignUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class RegisterUserCommand implements Command {
    private UserController userController;
    private Scanner scanner;

    @Autowired
    public RegisterUserCommand(UserController userController) {

        this.userController = userController;
        scanner = new Scanner(System.in);
    }

    @Override
    public boolean macthes(int input) {
        if (input == CommandKeyword.SIGNUP_USER) {
            return true;
        }
        return false;
    }

    @Override
    public void execute() {
        SignUpUserRequestDto request = new SignUpUserRequestDto();
        System.out.println("Enter the email id");
        String email = scanner.next();
        System.out.println("Enter the Password");
        String password = scanner.next();
        request.setEmail(email);
        request.setPassword(password);
        SignUpUserResponseDto response = userController.signUpUser(request);
        System.out.println(response.getMessage());
    }
}
