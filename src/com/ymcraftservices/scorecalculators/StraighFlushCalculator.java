package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;

public class StraighFlushCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        return new StraightCalculator().apply(hand);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new SquareCalculator();
    }
}
