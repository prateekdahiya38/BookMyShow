package com.project.bookmyshow;

import com.project.bookmyshow.commands.CommandRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {
    private Scanner scanner;
    private CommandRegistry commandRegistry;

    @Autowired
    public BookMyShowApplication(CommandRegistry commandRegistry) {
        this.scanner = new Scanner(System.in);
        this.commandRegistry = commandRegistry;
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Tell what do you want to do?");
            System.out.println("(1) -> Signup || (2) -> UpdateUser");
            int input = scanner.nextInt();
            commandRegistry.execute(input);
        }
    }


    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);

    }

}
