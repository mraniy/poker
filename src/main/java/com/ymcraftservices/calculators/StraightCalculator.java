package com.ymcraftservices.calculators;

import com.ymcraftservices.message.StraightMessage;
import com.ymcraftservices.model.*;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardUtilsForStraightHands.isStraightHand;

public class StraightCalculator implements CustomScoreCalculator {

    StraightMessage straightMessage = new StraightMessage();

    @Override
    public Combination apply(Hand hand) {
        List<Card> allCards = hand.getAllCards();
        allCards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        List<NumberCard> numCards = getAllCards(allCards);
        NumberCard fourthHighestCard = getFourthHighestCard(allCards, numCards);
        return new Combination(CombinationScore.STRAIGHT,fourthHighestCard.getNumber(),straightMessage.apply(Arrays.asList(fourthHighestCard.toString())));
    }

    private List<NumberCard> getAllCards(List<Card> allCards) {
        return allCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
    }

    private NumberCard getFourthHighestCard(List<Card> allCards, List<NumberCard> numCards) {
        return Arrays.asList(0, 1, 2, 3)
                .stream()
                .filter(fromIndice -> isStraightHand(allCards, numCards, fromIndice))
                .map(integer -> {
                    List<Card> cards = allCards.subList(integer, integer + 4);
                    return cards.get(cards.size() - 1);
                })
                .map(Card::getNumberCard)
                .max((o1, o2) -> new CardComparatorForRepeatedCards().apply(o2,o1))
                .orElseGet(() -> null);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new BrelanCalculator();
    }
}
