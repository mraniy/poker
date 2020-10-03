package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class DoublePairFunction implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new PairFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.DOUBLEPAIR;
    }
}
