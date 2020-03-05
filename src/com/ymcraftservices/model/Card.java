package com.ymcraftservices.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.ymcraftservices.utils.ComparatorUtils.compareCards;

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
        return compareCards(card.getNumberCard(), this.getNumberCard());
    }


}
