package com.ymcraftservices.predicates;

import com.google.common.collect.ImmutableSet;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Map;

public class PairPredicate implements CustomPokerPredicate{


    @Override
    public CustomPokerPredicate getNext() {
        return new HighCardPredicate();
    }

    @Override
    public boolean test(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
        return  ImmutableSet.copyOf(numberCardAndItsOccurence.values()).size() ==2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(2L, 1L));
    }
}
