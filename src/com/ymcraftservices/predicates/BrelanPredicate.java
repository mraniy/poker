package com.ymcraftservices.predicates;

import com.google.common.collect.ImmutableSet;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Map;

public class BrelanPredicate implements CustomPokerPredicate{

    private static BrelanPredicate instance;

    public static BrelanPredicate getInstance(){
        if (instance == null) {
            instance = new BrelanPredicate();
        }
        return instance;
    }

    private BrelanPredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return DoublePairPredicate.getInstance();
    }

    @Override
    public boolean test(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
        return ImmutableSet.copyOf(numberCardAndItsOccurence.values()).size() == 2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(3L, 1L));
    }
}
