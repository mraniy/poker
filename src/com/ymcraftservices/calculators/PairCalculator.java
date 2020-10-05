package com.ymcraftservices.calculators;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class PairCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> pairPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(2L);
        Predicate<Map.Entry<NumberCard, Long>> kickersPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L);
        List<Map.Entry<NumberCard, Long>> pairEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), pairPredicate);
        List<Map.Entry<NumberCard, Long>> kickerEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), kickersPredicate);

        NumberCard pairCard = pairEntries.stream()
                .findFirst()
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .orElseGet(() -> null);

        List<NumberCard> kickers = kickerEntries.stream()
                .map(Map.Entry::getKey)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1, o2))
                .limit(3)
                .collect(Collectors.toList());

        List<NumberCard> numberCards = Stream.concat(Stream.of(pairCard), kickers.stream())
                .collect(Collectors.toList());
        return new Combination(CombinationScore.PAIR,calculate(numberCards), "High card to ".concat(kickers.get(0).toString()));
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new HighCardCalculator();
    }
}
