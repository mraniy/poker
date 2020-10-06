package com.ymcraftservices.utils;

import com.ymcraftservices.model.NumberCard;

import java.util.function.BiFunction;

public class CardComparatorForRepeatedCards implements BiFunction<NumberCard,NumberCard,Integer> {
    @Override
    public Integer apply(NumberCard numberCard1, NumberCard numberCard2) {
        int number1 = numberCard1.equals(NumberCard.AS) ? 14 : numberCard1.getNumber();
        int number2 = numberCard2.equals(NumberCard.AS) ? 14 : numberCard2.getNumber();
        return number2 -  number1;
    }
}
