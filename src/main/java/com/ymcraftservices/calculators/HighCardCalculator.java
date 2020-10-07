package com.ymcraftservices.calculators;

import com.ymcraftservices.message.HighCardMessage;
import com.ymcraftservices.model.*;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class HighCardCalculator implements CustomScoreCalculator {

    HighCardMessage highCardMessage = new HighCardMessage();

    @Override
    public Combination apply(Hand hand) {
        List<NumberCard> highCards = hand.getAllCards()
                .stream()
                .map(Card::getNumberCard)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1, o2))
                .limit(5)
                .collect(Collectors.toList());
        return new Combination(CombinationScore.HIGHCARD,calculate(highCards), highCardMessage.apply(Arrays.asList(highCards.get(0).toString())));
    }

    @Override
    public CustomScoreCalculator getNext() {
        return null;
    }
}
