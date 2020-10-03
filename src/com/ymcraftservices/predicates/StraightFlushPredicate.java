package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StraightFlushPredicate implements CustomPokerPredicate{

    private static StraightFlushPredicate instance;

    public static StraightFlushPredicate getInstance(){
        if (instance == null) {
            instance= new StraightFlushPredicate();
        }
        return instance;
    }

    private StraightFlushPredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return SquarePredicate.getInstance();
    }

    Optional<LabelCard> retrieveLabelCardOfFlush(List<Card> cards) {
        Map<LabelCard, Long> numberOfOccurencesLabel = cards.stream()
                .collect(Collectors.groupingBy(h -> h.getLabelCard(),
                        Collectors.counting()));
        Optional<Map.Entry<LabelCard, Long>> maybeFlush = numberOfOccurencesLabel.entrySet().stream()
                .filter(labelCardLongEntry -> labelCardLongEntry.getValue() >= 5)
                .findFirst();
        return maybeFlush.map(Map.Entry::getKey);
    }

    @Override
    public boolean test(Hand hand) {
        return retrieveLabelCardOfFlush(hand.getAllCards()).map(flushLabelCard -> {
            List<Card> cardsWithTheSameLabelOccuringMoreThanFiveTimes = hand.getAllCards().stream()
                    .filter(card -> card.getLabelCard().equals(flushLabelCard))
                    .collect(Collectors.toList());
            cardsWithTheSameLabelOccuringMoreThanFiveTimes.sort(Comparator.comparing(card -> card.getNumberCard().getNumber()));
            return areCardsStraightNotToAs(cardsWithTheSameLabelOccuringMoreThanFiveTimes);
        }).orElseGet(() -> false);
    }


     int getCount(List<Card> cards, int count, int i) {
        if (cards.get(i).getNumberCard().getNumber()
                == cards.get(i-1).getNumberCard().getNumber() + 1) {
            count++;
        } else {
            count = 0;
        }
        return count;
    }

    boolean areCardsStraightNotToAs(List<Card> cards) {
        int count = 0;
        for (int j = 1; j < cards.size(); j++) {
            count = getCount(cards, count, j);
            if (count == 4) {
                return true;
            }
        }

        return false;
    }
}