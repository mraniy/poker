package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.ymcraftservices.utils.CardOccurenceCalculator.getCardsCorrespondingToPredicate;

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
        Map<LabelCard, Long> numberOfOccurencesLabel = getLabelAndItsOccurences(cards);
        LabelCard labelCard = getFlushLabelCard(numberOfOccurencesLabel);
        return getFlushCards(cards, labelCard);
    }

    private List<Card> getFlushCards(List<Card> cards, LabelCard labelCard) {
        return cards.stream()
                .filter(card -> card.getLabelCard().equals(labelCard))
                .collect(Collectors.toList());
    }

    private LabelCard getFlushLabelCard(Map<LabelCard, Long> numberOfOccurencesLabel) {
        return numberOfOccurencesLabel.entrySet().stream()
                .filter(labelCardLongEntry -> labelCardLongEntry.getValue() >= 5)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseGet(() -> null);
    }

    private Map<LabelCard, Long> getLabelAndItsOccurences(List<Card> cards) {
        return cards.stream()
                .collect(Collectors.groupingBy(h -> h.getLabelCard(),
                        Collectors.counting()));
    }
}
