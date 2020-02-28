package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FullHouse extends Hand {

    public FullHouse(List<Card> cards) {
        this.cards = cards;
        next = Optional.of(new Flush(cards));
    }

    @Override
    public Boolean verify() {
        if (!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        // retrieve the two potential two of a kind and three of kind
        List<Map.Entry<NumberCard, Long>> entries = numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 3 || numberCardLongEntry.getValue() == 2)
                .collect(Collectors.toList());
        // a full house if necessarly a three of kind and (three or two of a kind) , this is what we are asserting here
        return maybeThreeOfAKind(entries)
                .map(numberCardLongEntry -> maybeThreeOrTwoOfAKind(entries, numberCardLongEntry))
                .orElseGet(() -> false);

    }

    private Boolean maybeThreeOrTwoOfAKind(List<Map.Entry<NumberCard, Long>> entries, Map.Entry<NumberCard, Long> numberCardLongEntry) {
        return entries.stream()
                .filter(numberCarLodngEntry1 -> !numberCarLodngEntry1.equals(numberCardLongEntry))
                .findFirst()
                .map(numberCardLongEntry1 -> numberCardLongEntry1.getValue() == 2 || numberCardLongEntry1.getValue() == 3)
                .orElseGet(() -> false);
    }

    private Optional<Map.Entry<NumberCard, Long>> maybeThreeOfAKind(List<Map.Entry<NumberCard, Long>> entries) {
        return entries.stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 3)
                .findFirst();
    }
}
