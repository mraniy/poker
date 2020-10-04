package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Map;
import java.util.Optional;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsAndTheirOccurences;

public class SquarePredicate implements CustomPokerPredicate{

    private static SquarePredicate instance;

    public static SquarePredicate getInstance(){
        if (instance == null) {
            instance= new SquarePredicate();
        }
        return instance;
    }

    private SquarePredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return FullHousePredicate.getInstance();
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