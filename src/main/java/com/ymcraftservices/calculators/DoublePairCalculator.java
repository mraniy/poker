package com.ymcraftservices.calculators;

import com.ymcraftservices.message.DoublePairMessage;
import com.ymcraftservices.model.*;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class DoublePairCalculator implements CustomScoreCalculator {

    DoublePairMessage doublePairMessage = new DoublePairMessage();

    @Override
    public Combination apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> doublePairPredicate =
                numberCardLongEntry -> numberCardLongEntry.getValue().equals(2L);
        List<Map.Entry<NumberCard, Long>> doublePairEntries =
                getCardsCorrespondingToPredicate(hand.getAllCards(), doublePairPredicate);

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
        String message = doublePairMessage.apply(Arrays.asList(doublePairs.get(0).toString(), doublePairs.get(1).toString(), kicker.toString()));
        return new Combination(CombinationScore.DOUBLEPAIR, calculate(numberCards), message);
    }


    @Override
    public CustomScoreCalculator getNext() {
        return new PairCalculator();
    }
}
