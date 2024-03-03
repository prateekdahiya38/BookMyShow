package com.project.bookmyshow.commands;

public interface Command {
    boolean macthes(int input);

    void execute();
}
