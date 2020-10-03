package com.ymcraftservices.predicates;

import com.google.common.collect.ImmutableSet;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FullHousePredicate implements CustomPokerPredicate{

    private static FullHousePredicate instance;

    public static FullHousePredicate getInstance(){
        if (instance == null) {
            instance = new FullHousePredicate();
        }
        return instance;
    }

    private FullHousePredicate() {

    }


    @Override
    public CustomPokerPredicate getNext() {
        return  FlushPredicate.getInstance();
    }

    private Optional<Map.Entry<NumberCard, Long>> maybeThreeOfAKind(List<Map.Entry<NumberCard, Long>> entries) {
        return entries.stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue() == 3)
                .findFirst();
    }

    @Override
    public boolean test(Hand hand) {
        Map<NumberCard, Long> numberCardAndItsOccurence = getCardsAndTheirOccurences(hand.getAllCards());
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
}