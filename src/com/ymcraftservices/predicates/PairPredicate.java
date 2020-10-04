package com.ymcraftservices.predicates;

import com.google.common.collect.ImmutableSet;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Map;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsAndTheirOccurences;

public class PairPredicate implements CustomPokerPredicate{

    private static PairPredicate instance;

    public static PairPredicate getInstance(){
        if (instance == null) {
            instance= new PairPredicate();
        }
        return instance;
    }

    private PairPredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return HighCardPredicate.getInstance();
    }

    @Override
    public boolean test(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
        return  ImmutableSet.copyOf(numberCardAndItsOccurence.values()).size() ==2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(2L, 1L));
    }
}
