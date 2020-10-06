package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.NumberCard;

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
                .collect(Collectors.toList());
    }
}
