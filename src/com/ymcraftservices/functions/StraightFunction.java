package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class StraightFunction implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new BrelanFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.STRAIGHT;
    }
}
