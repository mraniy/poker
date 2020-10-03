package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class PairFunction implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new HighCardFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.PAIR;
    }
}
