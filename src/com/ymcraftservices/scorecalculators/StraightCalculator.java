package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardUtilsForStraightHands.isStraightHand;

public class StraightCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        List<Card> allCards = hand.getAllCards();
        allCards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        List<NumberCard> numCards = allCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        return Arrays.asList(0, 1, 2, 3)
                .stream()
                .filter(fromIndice -> isStraightHand(allCards, numCards, fromIndice))
                .map(integer -> {
                    List<Card> cards = allCards.subList(integer, integer + 4);
                    return cards.get(cards.size() - 1);
                })
                .map(card -> card.getNumberCard().getNumber())
                .max(Integer::compareTo)
                .orElseGet(() -> -1);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new BrelanCalculator();
    }
}
