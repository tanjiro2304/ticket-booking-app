package com.vn.ticketbookingapp.dto;

import com.vn.ticketbookingapp.entities.Tickets;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
        private String userId;

        private String firstName;

        private String lastName;

        private String idType;

        private String userName;

        private String password;

        private String contactNo;

        private String emailId;

        List<Tickets> bookedTickets;
}
