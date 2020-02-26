package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;

public class DataFactory {


    public static Card getCard(LabelCard labelCard, NumberCard numberCard) {
        Card card = new Card(labelCard, numberCard);
        return card;
    }
}
