package com.ymcraftservices;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;

public class DataFactory {


    public static Card getCard(LabelCard labelCard, NumberCard numberCard) {
        Card card = new Card(labelCard, numberCard);
        return card;
    }
}
