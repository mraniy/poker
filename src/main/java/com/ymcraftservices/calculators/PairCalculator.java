package com.ymcraftservices.calculators;

import com.ymcraftservices.message.PairMessage;
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

public class PairCalculator implements CustomScoreCalculator {

    PairMessage pairMessage = new PairMessage();

    @Override
    public Combination apply(Hand hand) {
        Predicate<Map.Entry<NumberCard, Long>> pairPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(2L);
        Predicate<Map.Entry<NumberCard, Long>> kickersPredicate = numberCardLongEntry -> numberCardLongEntry.getValue().equals(1L);
        List<Map.Entry<NumberCard, Long>> pairEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), pairPredicate);
        List<Map.Entry<NumberCard, Long>> kickerEntries = getCardsCorrespondingToPredicate(hand.getAllCards(), kickersPredicate);

        NumberCard pairCard = getPairCard(pairEntries);
        List<NumberCard> kickers = getKickersCard(kickerEntries);
        List<NumberCard> numberCards = getAllCards(pairCard, kickers);

        return new Combination(CombinationScore.PAIR, calculate(numberCards), pairMessage.apply(Arrays.asList(pairCard.toString(), kickers.get(0).toString())));
    }

    private List<NumberCard> getAllCards(NumberCard pairCard, List<NumberCard> kickers) {
        return Stream.concat(Stream.of(pairCard), kickers.stream())
                .collect(Collectors.toList());
    }

    private List<NumberCard> getKickersCard(List<Map.Entry<NumberCard, Long>> kickerEntries) {
        return kickerEntries.stream()
                .map(Map.Entry::getKey)
                .sorted((o1, o2) -> new CardComparatorForRepeatedCards().apply(o1, o2))
                .limit(3)
                .collect(Collectors.toList());
    }

    private NumberCard getPairCard(List<Map.Entry<NumberCard, Long>> pairEntries) {
        return pairEntries.stream()
                .findFirst()
                .map(numberCardLongEntry -> numberCardLongEntry.getKey())
                .orElseGet(() -> null);
    }

    @Override
    public CustomScoreCalculator getNext() {
        return new HighCardCalculator();
    }
}
