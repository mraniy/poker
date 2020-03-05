package com.ymcraftservices.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Card implements Comparable {






    private LabelCard labelCard;

    private NumberCard numberCard;

    @Override
    public int compareTo(Object o) {
        Card card = (Card) o;
        int number1;
        int number2;
        if(this.getNumberCard().equals(NumberCard.AS)) {
            number1 = 14;
        } else number1 = this.getNumberCard().getNumber();

        if(card.getNumberCard().equals(NumberCard.AS)) {
            number2 = 14;
        } else number2 = card.getNumberCard().getNumber();
        return number1 - number2;
    }
}
