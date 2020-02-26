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
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        List<Map.Entry<NumberCard, Long>> entries = numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 3 || numberCardLongEntry.getValue() == 2)
                .collect(Collectors.toList());
        Optional<Map.Entry<NumberCard, Long>> maybeThreeOfaKind = entries.stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 3)
                .findFirst();
        if(maybeThreeOfaKind.isPresent()) {
            Optional<Map.Entry<NumberCard, Long>> maybeThreeOrTwoOfAkind = entries.stream()
                    .filter(numberCardLongEntry -> !numberCardLongEntry.equals(maybeThreeOfaKind.get()))
                    .findFirst();
            if(maybeThreeOrTwoOfAkind.isPresent()) {
                return maybeThreeOrTwoOfAkind.get().getValue() == 2 || maybeThreeOrTwoOfAkind.get().getValue() == 3;
            }
            return false;
        }
        return false;
    }
}
