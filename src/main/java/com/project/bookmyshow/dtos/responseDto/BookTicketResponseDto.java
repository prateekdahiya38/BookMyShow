package com.project.bookmyshow.dtos.responseDto;

import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketResponseDto {
    private int amount;
    private Long ticketId;
    private List<String> seatNo;
    private String auditoriumName;
    private String status;
    private String message;
}
