package com.ymcraftservices.service;

import com.ymcraftservices.contract.FlushHand;
import com.ymcraftservices.contract.StraightHand;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StraightFlush extends Hand implements FlushHand, StraightHand {
    public StraightFlush(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        LabelCard flushLabelCard = retrieveLabelCardOfFlush(this.cards);
        if(flushLabelCard == null) return false;
        List<Card> cardsWithTheSameLabelOccuringMoreThanFiveTimes = cards.stream()
                .filter(card -> card.getLabelCard().equals(flushLabelCard))
                .collect(Collectors.toList());
        cardsWithTheSameLabelOccuringMoreThanFiveTimes.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        return areCardsStraightNotToAs(cardsWithTheSameLabelOccuringMoreThanFiveTimes);
    }

    @Override
    public void setBestFiveCards() {
        LabelCard flushLabelCard = retrieveLabelCardOfFlush(this.cards);
        List<Card> cardsWithTheSameLabelOccuringMoreThanFiveTimes = cards.stream()
                .filter(card -> card.getLabelCard().equals(flushLabelCard))
                .collect(Collectors.toList());
        cardsWithTheSameLabelOccuringMoreThanFiveTimes.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
        Integer straightTo = getStraightTo(cardsWithTheSameLabelOccuringMoreThanFiveTimes);
        setBestFiveCards(cardsWithTheSameLabelOccuringMoreThanFiveTimes.subList(straightTo-5, straightTo));
    }


}
