package com.ymcraftservices.testhands.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

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
        if(this.getNumberCard().equals(NumberCard.AS)) return 1;
        else if(card.getNumberCard().equals(NumberCard.AS)) return -1;
        else return this.getNumberCard().getNumber() - card.getNumberCard().getNumber();
    }
}
