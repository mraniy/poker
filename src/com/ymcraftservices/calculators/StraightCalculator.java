package com.ymcraftservices.calculators;

import com.ymcraftservices.model.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardUtilsForStraightHands.isStraightHand;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class StraightCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        List<Card> allCards = hand.getAllCards();
        allCards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        List<NumberCard> numCards = allCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        Integer score = Arrays.asList(0, 1, 2, 3)
                .stream()
                .filter(fromIndice -> isStraightHand(allCards, numCards, fromIndice))
                .map(integer -> {
                    List<Card> cards = allCards.subList(integer, integer + 4);
                    return cards.get(cards.size() - 1);
                })
                .map(card -> card.getNumberCard().getNumber())
                .max(Integer::compareTo)
                .orElseGet(() -> -1);
        return new Combination(CombinationScore.CARRE,score);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new BrelanCalculator();
    }
}
