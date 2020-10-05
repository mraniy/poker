package com.ymcraftservices.calculators;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;

public class RoyalFlushCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        // impossible to have two royal flushes so no need to implement a specific method for Royal flush
        return new Combination(CombinationScore.ROYALFLUSH,0,"Royal Flush");
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new StraighFlushCalculator();
    }
}
