package com.vn.ticketbookingapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name="UserEntity.bookedTickets",
        attributeNodes = @NamedAttributeNode("bookedTickets"),
        subgraphs = {@NamedSubgraph(name = "bookedTickets", attributeNodes = @NamedAttributeNode("passengerList")),
                @NamedSubgraph(name = "bookedTickets", attributeNodes = @NamedAttributeNode("transportService"))})

@Table(name="tbl_user_entity")
public class UserEntity {
    @Id
    private String userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

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

    @OneToMany(mappedBy = "userEntity")
    private Set<Tickets> bookedTickets;
}
