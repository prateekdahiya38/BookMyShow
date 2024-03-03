package com.project.bookmyshow.commands;

import com.project.bookmyshow.commands.allCommands.RegisterUserCommand;
import com.project.bookmyshow.commands.allCommands.UpdateUserProfileCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry {
    private List<Command> commands;

    @Autowired
    public CommandRegistry(RegisterUserCommand registerUserCommand,
                           UpdateUserProfileCommand updateUserProfileCommand) {
        this.commands = new ArrayList<>();
        commands.add(registerUserCommand);
        commands.add(updateUserProfileCommand);
    }

    public void execute(int input){
        for (Command command : commands){
            if (command.macthes(input)){
                command.execute();
                break;
            }
        }
    }
}
