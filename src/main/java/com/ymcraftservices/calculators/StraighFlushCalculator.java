package com.ymcraftservices.calculators;

import com.ymcraftservices.message.StraightFlushMessage;
import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.List;

public class StraighFlushCalculator implements CustomScoreCalculator {

    StraightFlushMessage straightFlushMessage = new StraightFlushMessage();

    @Override
    public Combination apply(Hand hand) {
        Combination combination = new StraightCalculator().apply(hand);
        List<String> params= Arrays.asList(NumberCard.valueOf(combination.getStrenght()).getNext().toString());
        return new Combination(CombinationScore.KENTFLUSH, combination.getStrenght(),
                straightFlushMessage.apply(params));
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new SquareCalculator();
    }
}
