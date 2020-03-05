package com.ymcraftservices.contract;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface FlushHand {

    default LabelCard retrieveLabelCardOfFlush(List<Card> cards) {
        Map<LabelCard, Long> numberOfOccurencesLabel = cards.stream()
                .collect(Collectors.groupingBy(h -> h.getLabelCard(),
                        Collectors.counting()));
        Optional<Map.Entry<LabelCard, Long>> maybeFlush = numberOfOccurencesLabel.entrySet().stream()
                .filter(labelCardLongEntry -> labelCardLongEntry.getValue() >= 5)
                .findFirst();
        return maybeFlush.map(Map.Entry::getKey).orElseGet(() -> null);
    }
}
