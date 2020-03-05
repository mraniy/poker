package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;

import java.util.List;
import java.util.stream.Collectors;

public class Flush extends Hand {

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        LabelCard labelCard = retrieveLabelCardOfFlush();
        return labelCard != null;
    }

    @Override
    public void setBestFiveCards() {
        LabelCard labelCard = retrieveLabelCardOfFlush();
        List<Card> cards = this.cards.stream()
                .filter(card -> card.getLabelCard().equals(labelCard))
                .sorted()
                .limit(5)
                .collect(Collectors.toList());
        setBestFiveCards(cards);
    }
}
