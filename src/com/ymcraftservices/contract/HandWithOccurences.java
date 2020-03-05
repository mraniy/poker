package com.ymcraftservices.contract;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.NumberCard;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface HandWithOccurences {

    default Map<NumberCard, Long> getCardsAndTheirOccurences(List<Card> cards) {
        return cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
    }
}
