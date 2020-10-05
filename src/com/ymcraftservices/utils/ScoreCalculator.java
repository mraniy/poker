package com.ymcraftservices.utils;

import com.google.common.collect.Lists;
import com.ymcraftservices.model.NumberCard;

import java.util.List;
import java.util.stream.IntStream;

public class ScoreCalculator {

    public static Integer calculate(List<NumberCard> numberCards) {
        List<NumberCard> fromSmallestToGreatest = Lists.reverse(numberCards);
        return IntStream.range(0, fromSmallestToGreatest.size())
                .map(index -> getScoreCard(fromSmallestToGreatest, index))
                .reduce((left, right) -> left + right)
                .orElseGet(() -> 0);

    }

    private static int getScoreCard(List<NumberCard> fromSmallestToGreatest, int i) {
        double scoreCard;
        if (fromSmallestToGreatest.get(i).equals(NumberCard.AS)) {
            scoreCard = 14 * fromSmallestToGreatest.get(i).getNumber() * Math.pow(10, i);
        } else {
            scoreCard = fromSmallestToGreatest.get(i).getNumber() * Math.pow(10, i);
        }
        return (int) scoreCard;
    }

    public static Integer calculate(NumberCard numberCard, int coefficient) {
        Integer scoreCard;
        if (numberCard.equals(NumberCard.AS)) {
            scoreCard = 14 * coefficient;
        } else {
            scoreCard = numberCard.getNumber() * coefficient;
        }
        return scoreCard;
    }


}
