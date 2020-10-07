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

public class SquareCalculator implements CustomScoreCalculator {


    @Override
    public Combination apply(Hand hand) {
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

        List<NumberCard> numberCards = Stream.of(numberCardSquare, kicker).collect(Collectors.toList());
        String message = "Square of ".concat(numberCardSquare.toString()).concat(" with ").concat(kicker.toString()).concat(" as a kicker");
        return new Combination(CombinationScore.CARRE,calculate(numberCards), message);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new FullHouseCalculator();
    }
}
