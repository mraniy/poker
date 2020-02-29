package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;

import java.util.Arrays;
import java.util.LinkedList;
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
        hand = new HighCard(cards);
        if (hand.verify()) return hand;
        return null;
    }
}
