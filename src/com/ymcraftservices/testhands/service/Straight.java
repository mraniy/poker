package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Straight extends Hand {

    public Straight(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        this.cards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        return areCardsStraightNotToAs(this.cards) || isaStraightToAs();
    }

    @Override
    public void setBestFiveCards() {
        this.cards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        List<Card> cards = null;
        if(isaStraightToAs())  {
            cards = this.cards.stream()
                    .filter(this::isFromTenToAs)
                    .sorted()
                    .collect(Collectors.toList());
        } else if(areCardsStraightNotToAs(this.cards)) {
            Integer straightTo = getStraightTo(this.cards);
            cards = this.cards.subList(straightTo - 5, straightTo);

        }
        setBestFiveCards(cards);
    }

    private boolean isaStraightToAs() {
        return getCards()
                .stream()
                .filter(this::isFromTenToAs)
                .count() == 5;
    }
}
