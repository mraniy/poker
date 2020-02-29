package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;

import java.util.List;

public class Flush extends Hand {

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        LabelCard labelCard = retrieveLabelCardOfFlush();
        return labelCard != null;
    }
}
