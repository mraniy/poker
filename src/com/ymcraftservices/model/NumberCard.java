package com.ymcraftservices.model;



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

    static {
        AS.next = DEUX;
        DEUX.next = TROIS;
        TROIS.next = QUATRE;
        QUATRE.next = CINQ;
        CINQ.next = SIX;
        SIX.next = SEPT;
        SEPT.next = HUIT;
        HUIT.next = NEUF;
        NEUF.next = DIX;
        DIX.next = VALLEE;
        VALLEE.next = DAME;
        DAME.next = KING;
        KING.next = AS;

    }

    private Integer number;

    private NumberCard next;

    public Integer getNumber() {
        return number;
    }

    public NumberCard getNext() {
        return  next;
    }

    NumberCard(Integer number) {
        this.number = number;
    }
}
