package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;

import java.util.List;

public class FlushPredicate implements CustomPokerPredicate {

    private static FlushPredicate instance;

    public static FlushPredicate getInstance() {
        if (instance == null) {
            return new FlushPredicate();
        }
        return instance;
    }

    private FlushPredicate() {

    }

    @Override
    public CustomPokerPredicate getNext() {
        return StraightPredicate.getInstance();
    }

    List<Card> retrieveFlushCards(List<Card> cards) {
        return ComposableFlushPredicate.getInstance().apply(cards);
    }


    @Override
    public boolean test(Hand hand) {
        return retrieveFlushCards(hand.getAllCards()).size() == 5;
    }
}