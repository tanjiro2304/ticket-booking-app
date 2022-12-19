package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchObj {
    private String source;

    private String destination;

    private LocalDate dateOfJourney;

    private String serviceType;


}
