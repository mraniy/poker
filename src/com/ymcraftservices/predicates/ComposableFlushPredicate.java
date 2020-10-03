package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ComposableFlushPredicate implements Function<List<Card>,List<Card>> {

    private static ComposableFlushPredicate instance;

    public static ComposableFlushPredicate getInstance(){
        if (instance == null) {
            instance= new ComposableFlushPredicate();
        }
        return instance;
    }

    private ComposableFlushPredicate() {

    }

    @Override
    public List<Card> apply(List<Card> cards) {
        Map<LabelCard, Long> numberOfOccurencesLabel = cards.stream()
                .collect(Collectors.groupingBy(h -> h.getLabelCard(),
                        Collectors.counting()));
        Optional<Map.Entry<LabelCard, Long>> maybeFlush = numberOfOccurencesLabel.entrySet().stream()
                .filter(labelCardLongEntry -> labelCardLongEntry.getValue() >= 5)
                .findFirst();
        LabelCard labelCard = maybeFlush.map(Map.Entry::getKey).orElseGet(() -> null);
        return cards.stream()
                .filter(card -> card.getLabelCard().equals(labelCard)).
                        collect(Collectors.toList());
    }
}
