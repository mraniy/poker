package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class SquareFunction  implements CustomPokerFuncion {
    @Override
    public CustomPokerFuncion getNext() {
        return new FullHouseFunction();
    }

    @Override
    public Combination apply(Hand hand) {
        return Combination.CARRE;
    }
}