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
        int number1;
        int number2;
        if(numberCard1.equals(NumberCard.AS)) {
            number1 = 14;
        } else number1 = numberCard1.getNumber();

        if(numberCard2.equals(NumberCard.AS)) {
            number2 = 14;
        } else number2 = numberCard2.getNumber();
        return number2 - number1;

    }

    public Integer getNumber() {
        return number;
    }

    NumberCard(Integer number) {
        this.number = number;
    }
}
