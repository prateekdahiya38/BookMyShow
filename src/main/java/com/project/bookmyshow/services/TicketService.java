package com.project.bookmyshow.services;

import com.project.bookmyshow.exceptions.InvalidArgumentException;
import com.project.bookmyshow.exceptions.SeatNotAvailableException;
import com.project.bookmyshow.models.*;
import com.project.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;


    @Autowired
    public TicketService(SeatRepository seatRepository,
                         ShowSeatRepository showSeatRepository,
                         ShowRepository showRepository,
                         UserRepository userRepository,
                         TicketRepository ticketRepository)
    {
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }





    public Ticket bookTicket(List<Long> seatIds, Long userId, Long showId) throws InvalidArgumentException, SeatNotAvailableException {

        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);


        Optional<Show> showOptional = showRepository.findById(showId);
        if (showOptional.isEmpty()){
            throw new InvalidArgumentException("Show by " + showId + " doesn't exist");
        }
        List<ShowSeat> showSeats = getAndLockShowSeats(seats, showOptional);


        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            throw new InvalidArgumentException("User with id: " + userId + " doesn't exist.");
        }


        Ticket ticket = new Ticket();
        ticket.setBookedBy(userOptional.get());
        ticket.setTicketStatus(TicketStatus.PROCESSING);
        ticket.setShow(showOptional.get());
        ticket.setSeats(seats);
        ticket.setAmount(0);
        ticket.setTimeOfBooking(new Date());



        Ticket saveTicket = ticketRepository.save(ticket);
        return saveTicket;
    }



    @Transactional(isolation = Isolation.SERIALIZABLE, timeout = 2)
    public List<ShowSeat> getAndLockShowSeats(List<Seat> seats, Optional<Show> showOptional) throws SeatNotAvailableException {
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats, showOptional.get());
        for (ShowSeat showSeat : showSeats){
            if (!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.LOCKED)))
            {
                throw new SeatNotAvailableException();
            }
        }

        List<ShowSeat> saveShowSeats = new ArrayList<>();
        for (ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            saveShowSeats.add(showSeatRepository.save(showSeat));
        }

        return showSeats;
    }

}
