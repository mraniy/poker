package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class FullHouseFunction  implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new FlushFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.FULLHOUSE;
    }
}