package com.ymcraftservices.calculators;

import com.ymcraftservices.message.FullHouseMessage;
import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.CombinationScore;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class FullHouseCalculator implements CustomScoreCalculator {

    private FullHouseMessage fullHouseMessage = new FullHouseMessage();

    @Override
    public Combination apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> fullHousePredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L) || numberCardLongEntry.getValue().equals(2L);
        List<Map.Entry<NumberCard, Long>> entries = getCardsCorrespondingToPredicate(hand.getAllCards(), fullHousePredicate);
        List<NumberCard> cardsOccuringThreeTimes = retrieveNumberCardByNumberOfOccurences(entries, 3L);
        List<NumberCard> cardsOccuringTwoTimes = retrieveNumberCardByNumberOfOccurences(entries, 2L);
        if(cardsOccuringThreeTimes.size() == 2) {
            return getCombinationInCaseCardsOccuringThreeTimesTwice(cardsOccuringThreeTimes);
        } else {
            return getCombinationInCaseCardsWithTwoAndThreeOccurences(cardsOccuringThreeTimes, cardsOccuringTwoTimes);
        }
    }

    private Combination getCombinationInCaseCardsWithTwoAndThreeOccurences(List<NumberCard> cardsOccuringThreeTimes, List<NumberCard> cardsOccuringTwoTimes) {
        List<NumberCard> numberCards = Stream.concat(Stream.of(cardsOccuringThreeTimes.get(0)), Stream.of(cardsOccuringTwoTimes.get(0)))
                .collect(Collectors.toList());
        String message = fullHouseMessage.apply(Arrays.asList(cardsOccuringThreeTimes.get(0).toString(), cardsOccuringTwoTimes.get(0).toString()));
        return new Combination(CombinationScore.FULLHOUSE,calculate(numberCards),message);
    }

    private Combination getCombinationInCaseCardsOccuringThreeTimesTwice(List<NumberCard> cardsOccuringThreeTimes) {
        String message = fullHouseMessage.apply(Arrays.asList(cardsOccuringThreeTimes.get(0).toString(), cardsOccuringThreeTimes.get(1).toString()));
        return new Combination(CombinationScore.FULLHOUSE,calculate(cardsOccuringThreeTimes), message);
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
