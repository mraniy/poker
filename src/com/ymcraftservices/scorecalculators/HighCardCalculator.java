package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class HighCardCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
        List<NumberCard> highCards = hand.getAllCards()
                .stream()
                .map(Card::getNumberCard)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1, o2))
                .limit(5)
                .collect(Collectors.toList());
        return calculate(highCards);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return null;
    }
}
