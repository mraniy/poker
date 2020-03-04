package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;

import java.util.*;
import java.util.stream.Collectors;

public class Pair extends Hand {

    public Pair(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        return new HashSet<>(numberCardAndItsOccurence.values()).size() ==2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(2L,1L));
    }

    @Override
    public Hand getBestFiveCards() {
        return null;
    }
}
