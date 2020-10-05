package com.ymcraftservices.utils;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.predicates.ComposableStraightPredicate;

import java.util.List;

public class CardUtilsForStraightHands {

    public static boolean isStraightHand(List<Card> allCards, List<NumberCard> numberCards, Integer fromIndice) {
        List<Card> cardsSublist = allCards.subList(fromIndice, fromIndice + 4);
        List<Card> cards = ComposableStraightPredicate.getInstance().apply(numberCards, cardsSublist);
        return hasAllTheCardsTheirMatchingNextCard(cards);
    }

    public static boolean hasAllTheCardsTheirMatchingNextCard(List<Card> cards) {
        return cards.size() ==4;
    }
}
