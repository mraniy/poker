package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class FlushFunction implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new StraightFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.FLUSH;
    }
}
