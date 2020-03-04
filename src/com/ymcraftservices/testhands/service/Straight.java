package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;

import java.util.Comparator;
import java.util.List;

public class Straight extends Hand {

    public Straight(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        this.cards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        return areCardsStraightNotToAs(this.cards) || isaStraightToAs();
    }

    @Override
    public Hand getBestFiveCards() {
        return null;
    }

    private boolean isaStraightToAs() {
        return getCards()
                .stream()
                .filter(this::isFromTenToAs)
                .count() == 5;
    }
}
