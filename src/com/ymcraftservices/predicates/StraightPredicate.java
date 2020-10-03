package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StraightPredicate implements CustomPokerPredicate {


    @Override
    public CustomPokerPredicate getNext() {
        return new BrelanPredicate();
    }


    @Override
    public boolean test(Hand hand) {
        List<Card> allCards = hand.getAllCards();
        List<NumberCard> numberCards = allCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        allCards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        return Arrays.asList(0, 1, 2, 3)
                .stream()
                .filter(operand -> isSubListStraight(numberCards, allCards.subList(operand, operand + 4)))
                .findFirst()
                .isPresent();
    }

    public boolean isSubListStraight(List<NumberCard> numberCards, List<Card> cards) {
        long count = cards.stream()
                .filter(card -> numberCards.contains(card.getNumberCard().getNext()))
                .count();
        return count == 4L;
    }
}