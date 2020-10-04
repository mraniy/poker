package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;

import java.util.function.Function;

public interface CustomScoreCalculator extends Function<Hand,Integer> {

    CustomScoreCalculator getNext();
}
