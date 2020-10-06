package com.ymcraftservices.model;


import java.util.Arrays;

public enum NumberCard {

    AS(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    static {
        AS.next = TWO;
        TWO.next = THREE;
        THREE.next = FOUR;
        FOUR.next = FIVE;
        FIVE.next = SIX;
        SIX.next = SEVEN;
        SEVEN.next = EIGHT;
        EIGHT.next = NINE;
        NINE.next = TEN;
        TEN.next = JACK;
        JACK.next = QUEEN;
        QUEEN.next = KING;
        KING.next = AS;

    }

    public static NumberCard valueOf(int value) {
        NumberCard numberCard1 = Arrays.stream(values())
                .filter(numberCard -> numberCard.getNumber() == value)
                .findFirst()
                .orElseGet(() -> null);
        return numberCard1;
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
