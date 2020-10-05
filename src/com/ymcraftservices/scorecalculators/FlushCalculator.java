package com.ymcraftservices.scorecalculators;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.utils.CardComparatorForRepeatedCards;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.ScoreCalculator.calculate;

public class FlushCalculator implements CustomScoreCalculator {


    @Override
    public Integer apply(Hand hand) {
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
        return calculate(numberCards);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new StraightCalculator();
    }
}
