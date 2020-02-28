package com.ymcraftservices.testhands.model;


import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Data
public abstract class Hand {

    protected List<Card> cards;

    protected Optional<Hand> next;




    protected LabelCard retrieveLabelCardOfFlush() {
        Map<LabelCard, Long> numberOfOccurencesLabel = cards.stream()
                .collect(Collectors.groupingBy(h -> h.getLabelCard(),
                        Collectors.counting()));
        Optional<Map.Entry<LabelCard, Long>> maybeFlush = numberOfOccurencesLabel.entrySet().stream()
                .filter(labelCardLongEntry -> labelCardLongEntry.getValue() >= 5)
                .findFirst();
        return maybeFlush.map(Map.Entry::getKey).orElseGet(() -> null);
    }

    protected boolean isFromTenToAs(Card card) {
        return Arrays.asList(NumberCard.AS, NumberCard.KING, NumberCard.DAME, NumberCard.VALLEE, NumberCard.DIX)
                .contains(card.getNumberCard());

    }


    protected boolean areCardsStraightNotToAs(List<Card> cardsWithTheSameLabelOccuringMoreThanFiveTimes) {
        int count = 0;
        for (int i = 0; i < cardsWithTheSameLabelOccuringMoreThanFiveTimes.size(); i++) {
            if (i + 1 < cardsWithTheSameLabelOccuringMoreThanFiveTimes.size()) {
                if (cardsWithTheSameLabelOccuringMoreThanFiveTimes.get(i + 1).getNumberCard().ordinal()
                        == cardsWithTheSameLabelOccuringMoreThanFiveTimes.get(i).getNumberCard().ordinal() + 1) {
                    count++;
                } else {
                    count = 0;
                }
            }
        }
        if (count == 4) {
            return true;
        }
        return false;
    }


    public Boolean isAValidHand() {
        if (this.getCards().size() != 7) {
            return false;
        }
        return true;
    }

    public abstract Boolean verify();

    public Hand determineHand()  {
        if(!this.verify()) {
            next.ifPresent(hand -> hand.determineHand());
        }
        return this;
    }


}
