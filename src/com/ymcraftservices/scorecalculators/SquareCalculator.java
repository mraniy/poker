package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;
import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class SquareCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> squarePredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(4L);
        Predicate<Map.Entry<NumberCard, Long>> kickersPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L);
        List<Map.Entry<NumberCard, Long>> squareEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), squarePredicate);
        List<Map.Entry<NumberCard, Long>> kickersEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), kickersPredicate);

        NumberCard numberCardSquare = squareEntries.stream().findFirst()
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .orElseGet(() -> null);

        NumberCard kicker = kickersEntries.stream()
                .filter(kickersPredicate)
                .map(Map.Entry::getKey)
                .max((o1, o2) ->  new CardComparatorForRepeatedCards().apply(o2, o1))
                .orElseGet(() -> null);
        Integer scoreMax = calculate(numberCardSquare, 10);
        Integer scoreKicker = calculate(kicker, 1);
        return scoreKicker+ scoreMax;
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new FullHouseCalculator();
    }
}
