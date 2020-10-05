package com.ymcraftservices.scorecalculators;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class DoublePairCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> doublePairPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(2L);
        List<Map.Entry<NumberCard, Long>> doublePairEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), doublePairPredicate);
        List<NumberCard> doublePairs = doublePairEntries.stream()
                .map(Map.Entry::getKey)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1, o2))
                .limit(2)
                .collect(Collectors.toList());

        NumberCard kicker = hand.getAllCards().stream()
                .filter(card -> !doublePairs.contains(card.getNumberCard()))
                .map(Card::getNumberCard)
                .max((o1, o2) -> new CardComparatorForRepeatedCards().apply(o2, o1))
                .orElseGet(() -> null);

        List<NumberCard> numberCards = Stream.concat(doublePairs.stream(),
                Stream.of(kicker))
                .collect(Collectors.toList());
        return calculate(numberCards);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new PairCalculator();
    }
}
