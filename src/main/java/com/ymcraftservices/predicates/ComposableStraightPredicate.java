package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ComposableStraightPredicate implements BiFunction<List<NumberCard> , List<Card>, List<Card>> {

    private static ComposableStraightPredicate instance;

    public static ComposableStraightPredicate getInstance(){
        if (instance == null) {
            instance= new ComposableStraightPredicate();
        }
        return instance;
    }

    private ComposableStraightPredicate() {

    }
    @Override
    public List<Card> apply(List<NumberCard> numberCards, List<Card> cards) {
        return cards.stream()
                .filter(card -> numberCards.contains(card.getNumberCard().getNext()))
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1.getNumberCard(),o2.getNumberCard()))
                .collect(Collectors.toList());
    }
}
