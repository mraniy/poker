package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;

import java.util.function.Function;

public class BrelanFunction implements CustomPokerFunction {
    @Override
    public CustomPokerFunction getNext() {
        return new DoublePairFunction();
    }

    @Override
    public Combination apply(Hand hand, Function<Hand,Integer> handStrenghtCalculator) {
        return new Combination(CombinationScore.BRELAN,handStrenghtCalculator.apply(hand));
    }
}
