package com.ymcraftservices.service;

import com.ymcraftservices.contract.HandWithOccurences;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Pair extends Hand implements HandWithOccurences {

    private Integer kicker;

    public Pair(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if (!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(this.cards);
        return new HashSet<>(numberCardAndItsOccurence.values()).size() == 2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(2L, 1L));
    }


    @Override
    public void setBestFiveCards() {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(this.cards);
        numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(2L))
                .findFirst()
                .ifPresent(numberCardLongEntry -> {
                    Stream<Card> pairStream = this.getCards().stream()
                            .filter(card -> card.getNumberCard().equals(numberCardLongEntry.getKey()));
                    Stream<Card> streamOtherCards = this.getCards().stream()
                            .filter(card -> !card.getNumberCard().equals(numberCardLongEntry.getKey()))
                            .sorted((o1, o2) -> NumberCard.compare(o1.getNumberCard(), o2.getNumberCard()))
                            .limit(3);
                    List<Card> cards = Stream.concat(pairStream, streamOtherCards)
                            .collect(Collectors.toList());
                    setBestFiveCards(cards);
                    setKicker(cards.get(2).getNumberCard().getNumber());
                });
    }
}