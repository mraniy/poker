package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;

public class HighCardCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        return 1;
    }

    @Override
    public CustomScoreCalculator getNext() {
        return null;
    }
}
