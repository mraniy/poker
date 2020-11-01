package com.ymcraftservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Player {

    private String lastName;

    private String firstName;

    private Integer potScore;

    private Hand hand;

    public String getFullName() {
        return firstName+ " "+lastName;
    }


}
