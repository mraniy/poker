package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Map;

public class DoublePairPredicate implements CustomPokerPredicate{


    @Override
    public CustomPokerPredicate getNext() {
        return new PairPredicate();
    }

    @Override
    public boolean test(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
        long numberOfPairs = getNumberOfPairs(numberCardAndItsOccurence);
        return numberOfPairs >= 2;
    }

    private long getNumberOfPairs(Map<NumberCard, Long> numberCardAndItsOccurence) {
        return numberCardAndItsOccurence.values()
                .stream()
                .filter(aLong -> aLong.equals(2L))
                .count();
    }
}

