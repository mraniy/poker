package com.ymcraftservices.calculators;

import com.ymcraftservices.message.SquareMessage;
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

public class SquareCalculator implements CustomScoreCalculator {

    SquareMessage squareMessage = new SquareMessage();

    @Override
    public Combination apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> squarePredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(4L);
        Predicate<Map.Entry<NumberCard, Long>> kickersPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L);
        List<Map.Entry<NumberCard, Long>> squareEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), squarePredicate);
        List<Map.Entry<NumberCard, Long>> kickersEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), kickersPredicate);

        NumberCard numberCardSquare = getNumberCardSquare(squareEntries);
        NumberCard kicker = getKicker(kickersPredicate, kickersEntries);
        List<NumberCard> numberCards = getAllCards(numberCardSquare, kicker);
        String message = squareMessage.apply(Arrays.asList(numberCardSquare.toString(),kicker.toString()));
        return new Combination(CombinationScore.CARRE,calculate(numberCards), message);
    }

    private List<NumberCard> getAllCards(NumberCard numberCardSquare, NumberCard kicker) {
        return Stream.of(numberCardSquare, kicker).collect(Collectors.toList());
    }

    private NumberCard getKicker(Predicate<Map.Entry<NumberCard, Long>> kickersPredicate, List<Map.Entry<NumberCard, Long>> kickersEntries) {
        return kickersEntries.stream()
                .filter(kickersPredicate)
                .map(Map.Entry::getKey)
                .max((o1, o2) ->  new CardComparatorForRepeatedCards().apply(o2, o1))
                .orElseGet(() -> null);
    }

    private NumberCard getNumberCardSquare(List<Map.Entry<NumberCard, Long>> squareEntries) {
        return squareEntries.stream().findFirst()
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .orElseGet(() -> null);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new FullHouseCalculator();
    }
}
