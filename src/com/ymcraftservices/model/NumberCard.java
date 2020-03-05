package com.ymcraftservices.model;

import static com.ymcraftservices.utils.ComparatorUtils.compareCards;

public enum NumberCard {

    AS(1),
    DEUX(2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6),
    SEPT(7),
    HUIT(8),
    NEUF(9),
    DIX(10),
    VALLEE(11),
    DAME(12),
    KING(13);

    private Integer number;

    public static int compare(NumberCard numberCard1 , NumberCard numberCard2) {
        return compareCards(numberCard1,numberCard2);

    }

    public Integer getNumber() {
        return number;
    }

    NumberCard(Integer number) {
        this.number = number;
    }
}
