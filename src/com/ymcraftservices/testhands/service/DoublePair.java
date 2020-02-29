package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoublePair extends Hand {

    public DoublePair(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        long numberOfPairs = numberCardAndItsOccurence.values()
                .stream()
                .filter(aLong -> aLong.equals(2L))
                .count();
        return numberOfPairs >= 2;
    }
}
