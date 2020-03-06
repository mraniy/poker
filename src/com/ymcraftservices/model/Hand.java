package com.ymcraftservices.model;


import lombok.Data;

import java.util.List;


@Data
public abstract class Hand {

    protected List<Card> cards;

    protected List<Card> bestFiveCards;

    protected Card kicker;


    public Boolean isAValidHand() {
        if (this.getCards().size() != 7) {
            return false;
        }
        return true;
    }

    public abstract Boolean verify();

    public abstract void setBestFiveCards();




}
