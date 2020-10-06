package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Hand;

import java.util.function.Predicate;

public interface CustomPokerPredicate extends Predicate<Hand> {


    CustomPokerPredicate getNext();

}
