package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HighCard extends Hand {

    public HighCard(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        List<Long> listOcuurences = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()))
                .values()
                .stream()
                .distinct()
                .collect(Collectors.toList());
        return listOcuurences.size() == 1 && listOcuurences.get(0).equals(1L);

    }

    @Override
    public Hand getBestFiveCards() {
        return null;
    }
}
