package com.ymcraftservices.model;


import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
public abstract class Hand {

    protected List<Card> cards;

    protected List<Card> bestFiveCards;




    public Boolean isAValidHand() {
        if (this.getCards().size() != 7) {
            return false;
        }
        return true;
    }

    public abstract Boolean verify();

    public abstract void setBestFiveCards();




}
