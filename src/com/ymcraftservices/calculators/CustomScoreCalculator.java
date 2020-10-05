package com.ymcraftservices.calculators;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

import java.util.function.Function;

public interface CustomScoreCalculator extends Function<Hand, Combination> {

    CustomScoreCalculator getNext();
}
