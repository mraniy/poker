package com.ymcraftservices.service;

import com.ymcraftservices.contract.HandWithOccurences;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@EqualsAndHashCode(callSuper = false)
public class FullHouse extends Hand implements HandWithOccurences {

    private Integer from;

    private Integer to;

    public FullHouse(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public Boolean verify() {
        if (!isAValidHand()) return false;
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(this.cards);
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
    public void setBestFiveCards() {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(this.cards);
        List<Map.Entry<NumberCard, Long>> entriesByThree = retrieveCardsWithOccurences(numberCardAndItsOccurence, 3L);
        entriesByThree.sort((o1, o2) -> NumberCard.compare(o1.getKey() , o2.getKey()));
        this.from = entriesByThree.get(0).getKey().getNumber();
        if (entriesByThree.size() >= 2) {
            this.to = entriesByThree.get(1).getKey().getNumber();
        } else {
            List<Map.Entry<NumberCard, Long>> entriesByTwo = retrieveCardsWithOccurences(numberCardAndItsOccurence, 2L);
            this.to = entriesByTwo.get(0).getKey().getNumber();
        }
        buildBestFullHouseHand();
    }

    private List<Map.Entry<NumberCard, Long>> retrieveCardsWithOccurences(Map<NumberCard, Long> numberCardAndItsOccurence, long l) {
        return numberCardAndItsOccurence.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == l)
                .collect(Collectors.toList());
    }

    private void buildBestFullHouseHand() {
        Stream<Card> cardFromStream = this.cards.stream().filter(card -> card.getNumberCard().getNumber().equals(this.from));
        Stream<Card> cardToStream = this.cards.stream().filter(card -> card.getNumberCard().getNumber().equals(this.to)).limit(2);
        List<Card> bestFiveCards = Stream.concat(cardFromStream, cardToStream).collect(Collectors.toList());
        setBestFiveCards(bestFiveCards);
        this.setFrom(this.from);
        this.setTo(this.to);
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
