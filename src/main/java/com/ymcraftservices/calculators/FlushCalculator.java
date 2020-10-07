package com.ymcraftservices.calculators;

import com.ymcraftservices.message.FlushMessage;
import com.ymcraftservices.model.*;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class FlushCalculator implements CustomScoreCalculator {

    FlushMessage flushMessage = new FlushMessage();

    @Override
    public Combination apply(Hand hand) {
        Map<LabelCard, Long> numberOfOccurencesLabel = hand.getAllCards().stream()
                .collect(Collectors.groupingBy(h -> h.getLabelCard(),
                        Collectors.counting()));

        LabelCard labelCard = numberOfOccurencesLabel.entrySet().stream()
                .filter(labelCardLongEntry -> labelCardLongEntry.getValue() >= 5)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseGet(() -> null);

        List<NumberCard> numberCards = hand.getAllCards()
                .stream()
                .filter(card -> card.getLabelCard().equals(labelCard))
                .map(Card::getNumberCard)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1, o2))
                .collect(Collectors.toList());

        return new Combination(CombinationScore.FLUSH,calculate(numberCards),flushMessage.apply(Arrays.asList(numberCards.get(0).toString())));
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new StraightCalculator();
    }
}