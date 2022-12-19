package com.vn.ticketbookingapp.converter;

import com.vn.ticketbookingapp.dto.User;
import com.vn.ticketbookingapp.entities.UserEntity;

public class UserConverter {
    public static User convertToDTO(UserEntity userEntity){
        return User.builder().
                firstName(userEntity.getFirstName()).
                lastName(userEntity.getLastName()).
                idType(userEntity.getIdType()).
                userId(userEntity.getUserId()).
                contactNo(userEntity.getContactNo()).
                emailId(userEntity.getEmailId()).
//                bookedTickets(userEntity.getBookedTickets()).
                build();
    }
    public static User convertToUserEntity(User user){
        return User.builder().
                firstName(user.getFirstName()).
                lastName(user.getLastName()).
                idType(user.getIdType()).
                userId(user.getUserId()).
                contactNo(user.getContactNo()).
                emailId(user.getEmailId()).
                bookedTickets(user.getBookedTickets()).
                build();
    }

}
