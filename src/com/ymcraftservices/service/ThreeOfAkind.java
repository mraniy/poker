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
public class ThreeOfAkind extends Hand implements HandWithOccurences {

    private Integer kicker;

    public ThreeOfAkind(List<Card> cards) {
        this.cards = cards;

    }

    @Override
    public Boolean verify() {
        if (!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(this.cards);
        return new HashSet<>(numberCardAndItsOccurence.values()).size() == 2 && numberCardAndItsOccurence.values().containsAll(Arrays.asList(3L, 1L));
    }

    @Override
    public void setBestFiveCards() {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(this.cards);
        numberCardAndItsOccurence.entrySet().stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L))
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(numberCard -> {
                    Stream<Card> cardsThreeOfKind = this.cards.stream()
                            .filter(card -> card.getNumberCard().equals(numberCard));
                    Stream<Card> kickers = this.cards.stream()
                            .filter(card -> !card.getNumberCard().equals(numberCard))
                            .sorted(Comparator.reverseOrder())
                            .limit(2);
                    List<Card> cards = Stream.concat(cardsThreeOfKind, kickers).collect(Collectors.toList());
                    setBestFiveCards(cards);
                    setKicker(cards.get(3).getNumberCard().getNumber());
                });
    }
}
