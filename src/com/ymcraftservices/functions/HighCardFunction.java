package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;

import java.util.function.Function;

public class HighCardFunction implements CustomPokerFunction {

    @Override
    public Combination apply(Hand hand, Function<Hand,Integer> handStrenghtCalculator) {
        return new Combination(CombinationScore.HIGHCARD,handStrenghtCalculator.apply(hand));
    }


    @Override
    public CustomPokerFunction getNext() {
        return null;
    }
}
