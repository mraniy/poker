package com.ymcraftservices.calculators;

import com.ymcraftservices.model.*;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardUtilsForStraightHands.isStraightHand;

public class StraightCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        List<Card> allCards = hand.getAllCards();
        allCards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        List<NumberCard> numCards = allCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        NumberCard fourthHighestCard = Arrays.asList(0, 1, 2, 3)
                .stream()
                .filter(fromIndice -> isStraightHand(allCards, numCards, fromIndice))
                .map(integer -> {
                    List<Card> cards = allCards.subList(integer, integer + 4);
                    return cards.get(cards.size() - 1);
                })
                .map(Card::getNumberCard)
                .max((o1, o2) -> new CardComparatorForRepeatedCards().apply(o2,o1))
                .orElseGet(() -> null);
        return new Combination(CombinationScore.CARRE,fourthHighestCard.getNumber(),"Straight to " + fourthHighestCard.getNext());
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new BrelanCalculator();
    }
}
