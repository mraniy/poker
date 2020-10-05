package com.ymcraftservices.calculators;

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

public class BrelanCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> brelanPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L);
        Predicate<Map.Entry<NumberCard, Long>> kickersPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L);
        List<Map.Entry<NumberCard, Long>> brelanEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), brelanPredicate);
        List<Map.Entry<NumberCard, Long>> kickersEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), kickersPredicate);

        NumberCard numberCardBrelan = brelanEntries.stream().findFirst()
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .orElseGet(() -> null);

        List<NumberCard> kickers = kickersEntries.stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L))
                .map(Map.Entry::getKey)
                .sorted((o1, o2) ->  new CardComparatorForRepeatedCards().apply(o1, o2))
                .limit(2)
                .collect(Collectors.toList());


        List<NumberCard> numberCards = Stream.concat(Stream.of(numberCardBrelan), kickers.stream())
                .collect(Collectors.toList());

        return calculate(numberCards);
    }



    @Override
    public CustomScoreCalculator getNext() {
        return new DoublePairCalculator();
    }
}
