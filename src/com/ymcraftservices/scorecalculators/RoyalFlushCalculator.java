package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;

public class RoyalFlushCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        // impossible to have two royal flushes so no need to implement a specific method for Royal flush
        return 0;
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new StraighFlushCalculator();
    }
}
