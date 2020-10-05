package com.ymcraftservices.calculators;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

public class StraighFlushCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        Combination combination = new StraightCalculator().apply(hand);

        return new Combination(combination.getCombinationScore(), combination.getStrenght(),
                combination.getMessage().replace("Straight"," Straight flush"));
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new SquareCalculator();
    }
}
