package com.ymcraftservices.testhands.model;

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

    NumberCard(Integer number) {
        this.number = number;
    }
}
