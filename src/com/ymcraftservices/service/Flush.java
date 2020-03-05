package com.ymcraftservices.service;

import com.ymcraftservices.contract.FlushHand;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;

import java.util.List;
import java.util.stream.Collectors;

public class Flush extends Hand implements FlushHand {

    public Flush(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        LabelCard labelCard = retrieveLabelCardOfFlush(this.cards);
        return labelCard != null;
    }

    @Override
    public void setBestFiveCards() {
        LabelCard labelCard = retrieveLabelCardOfFlush(this.cards);
        List<Card> cards = this.cards.stream()
                .filter(card -> card.getLabelCard().equals(labelCard))
                .sorted()
                .limit(5)
                .collect(Collectors.toList());
        setBestFiveCards(cards);
    }
}
