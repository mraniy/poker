package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StraightPredicate implements CustomPokerPredicate {

    private static StraightPredicate instance;

    public static StraightPredicate getInstance(){
        if (instance == null) {
            instance= new StraightPredicate();
        }
        return instance;
    }

    private StraightPredicate() {

    }



    @Override
    public CustomPokerPredicate getNext() {
        return BrelanPredicate.getInstance();
    }


    @Override
    public boolean test(Hand hand) {
        List<Card> allCards = hand.getAllCards();
        List<NumberCard> numberCards = allCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        allCards.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        return Arrays.asList(0, 1, 2, 3)
                .stream()
                .anyMatch(fromIndice -> isStraightHand(allCards, numberCards, fromIndice));
    }

    private boolean isStraightHand(List<Card> allCards, List<NumberCard> numberCards, Integer fromIndice) {
        List<Card> cardsSublist = allCards.subList(fromIndice, fromIndice + 4);
        List<Card> cards = ComposableStraightPredicate.getInstance().apply(numberCards, cardsSublist);
        return hasAllTheCardsTheirMatchingNextCard(cards);
    }

    public boolean hasAllTheCardsTheirMatchingNextCard(List<Card> cards) {
        return cards.size() ==4;
    }

}