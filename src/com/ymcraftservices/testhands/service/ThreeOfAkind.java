package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class ThreeOfAkind extends Hand {

    private Integer kicker;

    public ThreeOfAkind(List<Card> cards) {
        this.cards = cards;

    }

    @Override
    public Boolean verify() {
        if (!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        return new HashSet<>(numberCardAndItsOccurence.values()).size() == 2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(3L, 1L));
    }

    @Override
    public Hand getBestFiveCards() {
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        return numberCardAndItsOccurence.entrySet().stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L))
                .map(Map.Entry::getKey)
                .findFirst()
                .map(numberCard -> {
                    Stream<Card> cardsThreeOfKind = this.cards.stream()
                            .filter(card -> card.getNumberCard().equals(numberCard));
                    Stream<Card> kickers = this.cards.stream()
                            .filter(card -> !card.getNumberCard().equals(numberCard))
                            .sorted(Comparator.reverseOrder())
                            .limit(2);
                    List<Card> cards = Stream.concat(cardsThreeOfKind, kickers).collect(Collectors.toList());
                    return buildThreeOfAkind(cards);
                })
                .orElseGet(() -> null);
    }

    private ThreeOfAkind buildThreeOfAkind(List<Card> cards) {
        ThreeOfAkind threeOfAkind = new ThreeOfAkind(cards);
        threeOfAkind.setKicker(cards.get(3).getNumberCard().getNumber());
        return threeOfAkind;
    }
}
