package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.List;

import static com.ymcraftservices.utils.CardUtilsForStraightHands.hasAllTheCardsTheirMatchingNextCard;

public class RoyalFlushPredicate implements CustomPokerPredicate{

    private static RoyalFlushPredicate instance;

    public static RoyalFlushPredicate getInstance(){
        if (instance == null) {
            instance= new RoyalFlushPredicate();
        }
        return instance;
    }

    private RoyalFlushPredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return  StraightFlushPredicate.getInstance();
    }

    @Override
    public boolean test(Hand hand) {
        List<Card> maybeStraightFlushCards = StraightFlushPredicate.getInstance().getMaybeStraightFlushCards(hand);
        return hasAllTheCardsTheirMatchingNextCard(maybeStraightFlushCards)
                && maybeStraightFlushCards.get(0).getNumberCard().getNext().equals(NumberCard.AS);
    }


}