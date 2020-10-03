package com.ymcraftservices.predicates;

import com.google.common.collect.ImmutableSet;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class SquarePredicate implements CustomPokerPredicate{


    @Override
    public CustomPokerPredicate getNext() {
        return new FullHousePredicate();
    }

    @Override
    public boolean test(Hand hand) {
        Optional<Map.Entry<NumberCard, Long>> maybeSquareEntry = getNumberCardOfSquare(hand);
        return maybeSquareEntry.isPresent();
    }

    private Optional<Map.Entry<NumberCard, Long>> getNumberCardOfSquare(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
        return numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 4L)
                .findFirst();
    }
}