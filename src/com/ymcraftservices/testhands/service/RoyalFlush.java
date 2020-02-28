package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;

import java.util.List;
import java.util.Optional;


public class RoyalFlush extends Hand {

    public RoyalFlush(List<Card> cards) {

        this.cards = cards;
        next = Optional.of(new StraightFlush(cards));
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        LabelCard labelCard = retrieveLabelCardOfFlush();
        return Optional.ofNullable(labelCard)
                .filter(this::isaStraightToAs)
                .isPresent();

    }

    private boolean isaStraightToAs(LabelCard labelCard) {
        return getCards()
                .stream().filter(card -> card.getLabelCard().equals(labelCard))
                .filter(this::isFromTenToAs)
                .count() == 5;
    }




}
