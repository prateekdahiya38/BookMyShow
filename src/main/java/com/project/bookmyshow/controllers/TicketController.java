package com.project.bookmyshow.controllers;

import com.project.bookmyshow.dtos.requestDto.BookTicketRequestDto;
import com.project.bookmyshow.dtos.responseDto.BookTicketResponseDto;
import com.project.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {

        this.ticketService = ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto request){
        return null;
    }
}
