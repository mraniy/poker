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
                count = getCount(cardsWithTheSameLabelOccuringMoreThanFiveTimes, count, i);
            }
        }
        if (count == 4) {
            return true;
        }
        return false;
    }

    private int getCount(List<Card> cardsWithTheSameLabelOccuringMoreThanFiveTimes, int count, int i) {
        if (cardsWithTheSameLabelOccuringMoreThanFiveTimes.get(i + 1).getNumberCard().getNumber()
                == cardsWithTheSameLabelOccuringMoreThanFiveTimes.get(i).getNumberCard().getNumber() + 1) {
            count++;
        } else {
            count = 0;
        }
        return count;
    }

    protected Integer getStraightTo(List<Card> cards) {
        int count = 0;
        int i = 0;
        while(i < cards.size()) {
            if (i + 1 < cards.size()) {
                count = getCount(cards, count, i);
            }
            i++;
        }
        if (count == 4) {
            return i;
        }
        return -1;
    }


    public Boolean isAValidHand() {
        if (this.getCards().size() != 7) {
            return false;
        }
        return true;
    }

    public abstract Boolean verify();

    public abstract Hand getBestFiveCards();




}
