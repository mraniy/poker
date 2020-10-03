package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.List;
import java.util.stream.Collectors;

public class StraightFlushPredicate implements CustomPokerPredicate{

    private static StraightFlushPredicate instance;

    public static StraightFlushPredicate getInstance(){
        if (instance == null) {
            instance= new StraightFlushPredicate();
        }
        return instance;
    }

    private StraightFlushPredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return SquarePredicate.getInstance();
    }


    @Override
    public boolean test(Hand hand) {
        List<Card> straightCards = getMaybeStraightFlushCards(hand);
        return straightCards.size()==4;
    }

    public List<Card> getMaybeStraightFlushCards(Hand hand) {
        List<Card> flushedCards = ComposableFlushPredicate.getInstance().apply(hand.getAllCards());
        List<NumberCard> numberCards = flushedCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        return ComposableStraightPredicate.getInstance().apply(numberCards, flushedCards);
    }

}