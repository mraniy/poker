package com.ymcraftservices.calculators;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class StraighFlushCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        return new StraightCalculator().apply(hand);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new SquareCalculator();
    }
}
