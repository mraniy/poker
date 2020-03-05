package com.ymcraftservices.service;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = false)
public class Square extends Hand {

    private Integer fourOfAKind;

    private Integer kicker;

    public Square(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if (!isAValidHand()) return false;
        Optional<Map.Entry<NumberCard, Long>> maybeSquareEntry = getNumberCardOfSquare();
        return maybeSquareEntry.isPresent();
    }

    private Optional<Map.Entry<NumberCard, Long>> getNumberCardOfSquare() {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences();
        return numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 4L)
                .findFirst();
    }

    @Override
    public void setBestFiveCards() {
        Optional<Map.Entry<NumberCard, Long>> maybenumberCardOfSquare = getNumberCardOfSquare();
        NumberCard numberCard = maybenumberCardOfSquare
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .orElseGet(() -> null);
        this.fourOfAKind = numberCard.getNumber();
        Stream<Card> streamOfFour = this.cards.stream()
                .filter(card -> card.getNumberCard().getNumber() == numberCard.getNumber());
         this.cards.stream()
                .filter(card -> card.getNumberCard().getNumber() != numberCard.getNumber())
                .max(Comparator.comparingInt(o -> o.getNumberCard().getNumber()))
                .ifPresent(card -> {
                    this.kicker = card.getNumberCard().getNumber();
                    List<Card> bestFiveCards = Stream.concat(streamOfFour, Stream.of(card))
                            .collect(Collectors.toList());
                    setBestFiveCards(bestFiveCards);
                });


    }
}
