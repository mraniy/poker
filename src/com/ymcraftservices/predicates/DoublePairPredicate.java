package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Map;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsAndTheirOccurences;

public class DoublePairPredicate implements CustomPokerPredicate{

    private static DoublePairPredicate instance;

    public static DoublePairPredicate getInstance(){
        if (instance == null) {
            instance = new DoublePairPredicate();
        }
        return instance;
    }

    private DoublePairPredicate() {

    }

    @Override
    public CustomPokerPredicate getNext() {
        return  PairPredicate.getInstance();
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

