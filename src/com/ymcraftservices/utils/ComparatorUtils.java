package com.ymcraftservices.utils;

import com.ymcraftservices.model.NumberCard;

public class ComparatorUtils {

    public static int compareCards(NumberCard numberCard1, NumberCard numberCard2) {
        int number1;
        int number2;
        if(numberCard2.equals(NumberCard.AS)) {
            number1 = 14;
        } else number1 = numberCard2.getNumber();

        if(numberCard1.equals(NumberCard.AS)) {
            number2 = 14;
        } else number2 = numberCard1.getNumber();
        return number1 - number2;
    }
}
