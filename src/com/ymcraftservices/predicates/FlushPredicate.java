package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlushPredicate implements CustomPokerPredicate {

    private static FlushPredicate instance;

    public static FlushPredicate getInstance(){
        if (instance == null) {
            return new FlushPredicate();
        }
        return instance;
    }

    private FlushPredicate() {

    }

    @Override
    public CustomPokerPredicate getNext() {
        return  StraightPredicate.getInstance();
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
        return retrieveLabelCardOfFlush(hand.getAllCards()).isPresent();
    }
}