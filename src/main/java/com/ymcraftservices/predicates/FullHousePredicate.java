package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsAndTheirOccurences;

public class FullHousePredicate implements CustomPokerPredicate{

    private static FullHousePredicate instance;

    public static FullHousePredicate getInstance(){
        if (instance == null) {
            instance = new FullHousePredicate();
        }
        return instance;
    }

    private FullHousePredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return  FlushPredicate.getInstance();
    }


    @Override
    public boolean test(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
        List<Long> occurencesOfCards = numberCardAndItsOccurence.values()
                .stream()
                .filter(numberOfOccurences -> numberOfOccurences >= 2)
                .collect(Collectors.toList());
        return atLeastTwoOccurencesOfCards(occurencesOfCards) && atLeastOneTHREEOfAkind(occurencesOfCards);

    }

    private boolean atLeastTwoOccurencesOfCards(List<Long> occurencesOfCards) {
        return occurencesOfCards.size() >=2;
    }

    private boolean atLeastOneTHREEOfAkind(List<Long> occurencesOfCards) {
        return occurencesOfCards.contains(3L);
    }

}