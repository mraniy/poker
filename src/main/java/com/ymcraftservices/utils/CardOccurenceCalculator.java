package com.ymcraftservices.utils;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.NumberCard;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CardOccurenceCalculator {

    public static Map<NumberCard, Long> getCardsAndTheirOccurences(List<Card> cards) {
        return cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
    }

    public static List<Map.Entry<NumberCard, Long>> getCardsCorrespondingToPredicate(List<Card> cards, Predicate<Map.Entry<NumberCard, Long>> entryPredicate) {
        Map<NumberCard, Long> cardsAndTheirOccurences = getCardsAndTheirOccurences(cards);
        return cardsAndTheirOccurences.entrySet()
                .stream()
                .filter(entryPredicate)
                .collect(Collectors.toList());
    }


}
