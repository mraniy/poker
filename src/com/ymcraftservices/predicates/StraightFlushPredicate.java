package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardUtilsForStraightHands.hasAllTheCardsTheirMatchingNextCard;

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
        return hasAllTheCardsTheirMatchingNextCard(straightCards);
    }

    public List<Card> getMaybeStraightFlushCards(Hand hand) {
        List<Card> flushedCards = ComposableFlushPredicate.getInstance().apply(hand.getAllCards());
        List<NumberCard> numberCards = flushedCards.stream().map(Card::getNumberCard).collect(Collectors.toList());
        return ComposableStraightPredicate.getInstance().apply(numberCards, flushedCards);
    }

}