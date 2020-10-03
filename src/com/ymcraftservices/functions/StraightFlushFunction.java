package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class StraightFlushFunction  implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new SquareFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.KENTFLUSH;
    }
}