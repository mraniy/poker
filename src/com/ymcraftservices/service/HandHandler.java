package com.ymcraftservices.service;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.List;

public class HandHandler {

    public static Hand determine(List<Card> cards) {
        Hand hand = new RoyalFlush(cards);
        if (hand.verify()) return hand;
        hand = new StraightFlush(cards);
        if (hand.verify()) return hand;
        hand = new Square(cards);
        if (hand.verify()) return hand;
        hand = new FullHouse(cards);
        if (hand.verify()) return hand;
        hand = new Flush(cards);
        if (hand.verify()) return hand;
        hand = new Straight(cards);
        if (hand.verify()) return hand;
        hand = new ThreeOfAkind(cards);
        if (hand.verify()) return hand;
        hand = new DoublePair(cards);
        if (hand.verify()) return hand;
        hand = new Pair(cards);
        if (hand.verify()) return hand;
        else return new HighCard(cards);
    }

    public static void main(String[] args) {
        Card card1 = new Card(LabelCard.COEUR, NumberCard.DEUX);
        Card card = new Card(LabelCard.COEUR, NumberCard.AS);
        Card card2 = new Card(LabelCard.PIQUE, NumberCard.KING);
        Card card3 = new Card(LabelCard.CARREAU, NumberCard.DAME);
        Card card4 = new Card(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = new Card(LabelCard.PIQUE, NumberCard.DIX);
        Card card6 = new Card(LabelCard.PIQUE, NumberCard.VALLEE);
        Hand determine = determine(Arrays.asList(card, card1, card2, card3, card4, card5, card6));
        determine.setBestFiveCards();
        System.out.println(determine.getBestFiveCards());


    }
}
