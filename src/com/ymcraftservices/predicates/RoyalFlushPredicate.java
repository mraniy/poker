package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoyalFlushPredicate implements CustomPokerPredicate{

    private static RoyalFlushPredicate instance;

    public static RoyalFlushPredicate getInstance(){
        if (instance == null) {
            instance= new RoyalFlushPredicate();
        }
        return instance;
    }

    private RoyalFlushPredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return  StraightFlushPredicate.getInstance();
    }

    @Override
    public boolean test(Hand hand) {
        return retrieveLabelCardOfFlush(hand.getAllCards())
                .filter(labelCard -> isaStraightToAs(hand, labelCard))
                .isPresent();
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

    private boolean isaStraightToAs(Hand hand, LabelCard labelCard) {
        return hand.getAllCards()
                .stream().filter(card -> card.getLabelCard().equals(labelCard))
                .filter(this::isFromTenToAs)
                .count() == 5;
    }

    boolean isFromTenToAs(Card card) {
        return Arrays.asList(NumberCard.AS, NumberCard.KING, NumberCard.DAME, NumberCard.VALLEE, NumberCard.DIX)
                .contains(card.getNumberCard());

    }
}