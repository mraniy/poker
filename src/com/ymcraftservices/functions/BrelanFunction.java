package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class BrelanFunction implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new DoublePairFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.BRELAN;
    }
}
