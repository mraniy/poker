package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class FullHouseCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> fullHousePredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L) || numberCardLongEntry.getValue().equals(2L);
        List<Map.Entry<NumberCard, Long>> entries = getCardsCorrespondingToPredicate(hand.getAllCards(), fullHousePredicate);
        NumberCard maxNumberCard = retrieveNumberCardByNumberOfOccurences(entries, 3L);
        NumberCard minNumberCard = retrieveNumberCardByNumberOfOccurences(entries, 2L);
        return getScore(minNumberCard,maxNumberCard);
    }



    private Integer getScore(NumberCard minNumberCard,NumberCard maxNumberCard) {
        Integer scoreMax = calculate(maxNumberCard, 100);
        Integer scoreMin = calculate(minNumberCard, 10);
        return scoreMax + scoreMin;
    }



    private NumberCard retrieveNumberCardByNumberOfOccurences(List<Map.Entry<NumberCard, Long>> entries, long numberOfOccurences) {
        return entries.stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(numberOfOccurences))
                .map(Map.Entry::getKey)
                .max((o1, o2) ->
                        new CardComparatorForRepeatedCards().apply(o1, o2)
                ).orElseGet(() -> null);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new FlushCalculator();
    }
}
