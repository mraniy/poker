package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;

import java.util.function.Function;

public class StraightFlushFunction  implements CustomPokerFunction {
    @Override
    public CustomPokerFunction getNext() {
        return new SquareFunction();
    }


    @Override
    public Combination apply(Hand hand, Function<Hand,Integer> handStrenghtCalculator) {
        return new Combination(CombinationScore.KENTFLUSH,handStrenghtCalculator.apply(hand));
    }
}