package com.ymcraftservices.testhands.service;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.NumberCard;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = false)
public class FullHouse extends Hand {

    private Integer from;

    private Integer to;

    public FullHouse(List<Card> cards) {
        this.cards = cards;
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

    @Override
    public Hand getBestFiveCards() {
        Map<NumberCard, Long> numberCardAndItsOccurence = this.cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
        List<Map.Entry<NumberCard, Long>> entriesByThree = numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 3L)
                .collect(Collectors.toList());
        entriesByThree.sort((o1, o2) -> NumberCard.compare(o1.getKey() , o2.getKey()));
        this.from = entriesByThree.get(0).getKey().getNumber();
        if (entriesByThree.size() >= 2) {
            this.to = entriesByThree.get(1).getKey().getNumber();
        } else {
            List<Map.Entry<NumberCard, Long>> entriesByTwo = numberCardAndItsOccurence.entrySet()
                    .stream()
                    .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 2L)
                    .collect(Collectors.toList());
            this.to = entriesByTwo.get(0).getKey().getNumber();
        }
        return buildBestFullHouseHand();
    }

    private Hand buildBestFullHouseHand() {
        Stream<Card> cardFromStream = this.cards.stream().filter(card -> card.getNumberCard().getNumber().equals(this.from));
        Stream<Card> cardToStream = this.cards.stream().filter(card -> card.getNumberCard().getNumber().equals(this.to)).limit(2);
        FullHouse fullHouse = new FullHouse(Stream.concat(cardFromStream, cardToStream).collect(Collectors.toList()));
        fullHouse.setFrom(this.from);
        fullHouse.setTo(this.to);
        return fullHouse;
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
