package com.ymcraftservices.service;

import com.ymcraftservices.contract.FlushHand;
import com.ymcraftservices.contract.StraightHand;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class RoyalFlush extends Hand implements FlushHand, StraightHand {

    public RoyalFlush(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        LabelCard labelCard = retrieveLabelCardOfFlush(this.cards);
        return Optional.ofNullable(labelCard)
                .filter(this::isaStraightToAs)
                .isPresent();

    }

    @Override
    public void setBestFiveCards() {
        LabelCard labelCard = retrieveLabelCardOfFlush(this.cards);
        List<Card> bestFiveCards= this.cards.stream()
                .filter(card -> isaStraightToAs(card.getLabelCard()))
                .filter(card -> card.getLabelCard().equals(labelCard))
                .collect(Collectors.toList());
        setBestFiveCards(bestFiveCards);
    }

    private boolean isaStraightToAs(LabelCard labelCard) {
        return getCards()
                .stream().filter(card -> card.getLabelCard().equals(labelCard))
                .filter(this::isFromTenToAs)
                .count() == 5;
    }






}