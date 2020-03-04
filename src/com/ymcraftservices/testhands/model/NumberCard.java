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

    public static int compare(NumberCard numberCard1 , NumberCard numberCard2) {
        if(numberCard1.equals(NumberCard.AS)) return -1;
        else if(numberCard2.equals(NumberCard.AS)) return 1;
        else return numberCard1.getNumber() - numberCard2.getNumber();
    }

    public Integer getNumber() {
        return number;
    }

    NumberCard(Integer number) {
        this.number = number;
    }
}
