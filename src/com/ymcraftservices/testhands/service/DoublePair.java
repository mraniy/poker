package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class DoublePair extends Hand {

    private Integer kicker;

    public DoublePair(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if(!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences();
        long numberOfPairs = numberCardAndItsOccurence.values()
                .stream()
                .filter(aLong -> aLong.equals(2L))
                .count();
        return numberOfPairs >= 2;
    }

    @Override
    public Hand getBestFiveCards() {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences();
        List<NumberCard> numberCards = getTwoHighestCardsHavingTwoOccurences(numberCardAndItsOccurence);
        Stream<Card> cardsWithDoublePair = this.cards.stream()
                .filter(card -> numberCards.contains(card.getNumberCard()));
        return getKicker(numberCardAndItsOccurence)
                .map(numberCard -> {
                    List<Card> cards = this.cards.stream()
                            .filter(card -> card.getNumberCard().equals(numberCard))
                            .findFirst()
                            .map(card -> mergeKickerAndDoublePairs(cardsWithDoublePair, card)
                            ).orElseGet(() -> new ArrayList<>());
                    DoublePair doublePair = new DoublePair(cards);
                    doublePair.setKicker(numberCard.getNumber());
                    return doublePair;
                }).orElseGet(() -> null);

    }

    private List<Card> mergeKickerAndDoublePairs(Stream<Card> cardsWithDoublePair, Card card) {
        return Stream.concat(Arrays.asList(card).stream(), cardsWithDoublePair)
                .collect(Collectors.toList());
    }

    private Optional<NumberCard> getKicker(Map<NumberCard, Long> numberCardAndItsOccurence) {
        return numberCardAndItsOccurence.entrySet().stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L))
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .max((o1, o2) -> NumberCard.compare(o1, o2));
    }

    private List<NumberCard> getTwoHighestCardsHavingTwoOccurences(Map<NumberCard, Long> numberCardAndItsOccurence) {
        return numberCardAndItsOccurence.entrySet().stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(2L))
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .sorted((o1, o2) -> NumberCard.compare(o1, o2))
                .limit(2)
                .collect(Collectors.toList());
    }

    private Map<NumberCard, Long> getCardsAndTheirOccurences() {
        return this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
    }
}
