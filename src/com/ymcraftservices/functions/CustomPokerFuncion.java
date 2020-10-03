package com.ymcraftservices.functions;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;

import java.util.function.Function;

public interface CustomPokerFuncion extends Function<Hand, Combination> {
    CustomPokerFuncion getNext();
}
