package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsAndTheirOccurences;

public class FullHouseCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        Map<NumberCard, Long> cardsAndTheirOccurences = getCardsAndTheirOccurences(hand.getAllCards());
        List<Map.Entry<NumberCard, Long>> entries = cardsAndTheirOccurences.entrySet()
                .stream()
                .filter(numberCardLongEntry -> numberCardLongEntry.getValue().equals(3L) || numberCardLongEntry.getValue().equals(2L))
                .collect(Collectors.toList());
        NumberCard maxNumberCard = retrieveNumberCardByNumberOfOccurences(entries, 3L);
        NumberCard minNumberCard = retrieveNumberCardByNumberOfOccurences(entries, 2L);
        return getScore(minNumberCard,maxNumberCard);
    }

    private Integer getScore(NumberCard minNumberCard,NumberCard maxNumberCard) {
        Integer scoreMax = getScore(maxNumberCard, 200);
        Integer scoreMin = getScore(minNumberCard, 10);
        return scoreMax + scoreMin;
    }

    private Integer getScore(NumberCard maxNumberCard, int coefficient) {
        Integer scoreMaxCard;
        if(maxNumberCard.equals(NumberCard.AS)) {
            scoreMaxCard = 14 * coefficient;
        } else {
            scoreMaxCard = maxNumberCard.getNumber()*coefficient;
        }
        return scoreMaxCard;
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
