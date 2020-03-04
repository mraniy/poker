package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ThreeOfAkind extends Hand {

    public ThreeOfAkind(List<Card> cards) {
        this.cards = cards;

    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        return new HashSet<>(numberCardAndItsOccurence.values()).size() ==2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(3L,1L));
    }

    @Override
    public Hand getBestFiveCards() {
        return null;
    }
}
