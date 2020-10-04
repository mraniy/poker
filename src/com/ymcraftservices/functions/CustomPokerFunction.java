package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface CustomPokerFunction extends BiFunction<Hand,Function<Hand,Integer> , Combination> {
    CustomPokerFunction getNext();
}
