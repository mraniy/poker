package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class HighCardFunction implements CustomPokerFuncion {

    @Override
    public Combination apply(Hand hand) {
        return Combination.HIGHCARD;
    }

    @Override
    public CustomPokerFuncion getNext() {
        return null;
    }
}
