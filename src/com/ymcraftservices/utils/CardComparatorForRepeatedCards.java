package com.ymcraftservices.utils;

import com.ymcraftservices.model.NumberCard;

import java.util.function.BiFunction;

public class CardComparatorForRepeatedCards implements BiFunction<NumberCard,NumberCard,Integer> {
    @Override
    public Integer apply(NumberCard numberCard1, NumberCard numberCard2) {
        if (numberCard1.equals(NumberCard.AS)) return 1;
        else return numberCard1.getNumber() - numberCard2.getNumber();
    }
}
