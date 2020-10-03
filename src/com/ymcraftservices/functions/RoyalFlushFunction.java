package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class RoyalFlushFunction implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new StraightFlushFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.ROYALFLUSH;
    }
}
