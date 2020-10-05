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

public class FullHouseCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> fullHousePredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L) || numberCardLongEntry.getValue().equals(2L);
        List<Map.Entry<NumberCard, Long>> entries = getCardsCorrespondingToPredicate(hand.getAllCards(), fullHousePredicate);
        List<NumberCard> maxNumberCards = retrieveNumberCardByNumberOfOccurences(entries, 3L);
        List<NumberCard> minNumberCards = retrieveNumberCardByNumberOfOccurences(entries, 2L);
        if(maxNumberCards.size() == 2) {
            String message = getMessage(maxNumberCards.get(0).toString(), maxNumberCards.get(1).toString());
            return new Combination(CombinationScore.FULLHOUSE,calculate(maxNumberCards), message);
        } else {
            List<NumberCard> numberCards = Stream.concat(Stream.of(maxNumberCards.get(0)), Stream.of(minNumberCards.get(0)))
                    .collect(Collectors.toList());
            String message = getMessage(maxNumberCards.get(0).toString(), minNumberCards.get(0).toString());
            return new Combination(CombinationScore.FULLHOUSE,calculate(numberCards),message);
        }
    }

    private String getMessage(String maxFull, String minFull) {
        return "Full House".concat("from ".concat(maxFull))
                .concat("To ").concat(minFull);
    }


    private List<NumberCard> retrieveNumberCardByNumberOfOccurences(List<Map.Entry<NumberCard, Long>> entries, long numberOfOccurences) {
        return entries.stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(numberOfOccurences))
                .map(Map.Entry::getKey)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1,o2))
                .collect(Collectors.toList());
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new FlushCalculator();
    }
}
