package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;

    @Column(name="id_type")
    private String idType;

    @Column(name="user_name")
    private String userName;

    @Column
    private String password;

    @Column(name="contact_no")
    private String contactNo;

    @Column(name="email_id")
    private String emailId;

    @OneToMany(mappedBy = "user")
    List<Tickets> bookedTickets;
}
