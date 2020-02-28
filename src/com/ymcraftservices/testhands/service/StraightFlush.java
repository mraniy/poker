package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StraightFlush extends Hand {
    public StraightFlush(List<Card> cards) {
        this.cards = cards;
        next = Optional.of(new Square(cards));
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        LabelCard flushLabelCard = retrieveLabelCardOfFlush();
        if(flushLabelCard == null) return false;
        List<Card> cardsWithTheSameLabelOccuringMoreThanFiveTimes = cards.stream()
                .filter(card -> card.getLabelCard().equals(flushLabelCard))
                .collect(Collectors.toList());
        cardsWithTheSameLabelOccuringMoreThanFiveTimes.sort(Comparator.comparing(card -> card.getNumberCard().ordinal()));
        return areCardsStraightNotToAs(cardsWithTheSameLabelOccuringMoreThanFiveTimes);
    }


}
