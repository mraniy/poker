package com.ymcraftservices.model;


import com.ymcraftservices.functions.CustomPokerFunction;
import com.ymcraftservices.functions.RoyalFlushFunction;
import com.ymcraftservices.predicates.CustomPokerPredicate;
import com.ymcraftservices.predicates.RoyalFlushPredicate;
import com.ymcraftservices.scorecalculators.CustomScoreCalculator;
import com.ymcraftservices.scorecalculators.RoyalFlushCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@AllArgsConstructor
public class Hand {

    private List<Card> potcards;

    private List<Card> playercards;

    public List<Card> getAllCards() {
        return Stream.concat(potcards.stream(), playercards.stream())
                .collect(Collectors.toList());
    }

    public Integer getScore() {
        RoyalFlushPredicate highLevelPredicate =  RoyalFlushPredicate.getInstance();
        RoyalFlushFunction highLevelFunction = new RoyalFlushFunction();
        RoyalFlushCalculator highLevelCalculator = new RoyalFlushCalculator();
        Combination combination = getCombination(highLevelPredicate, highLevelFunction, highLevelCalculator);
        return combination.getCombinationScore().getScore() + combination.getStrenght();
    }

    private Combination getCombination(CustomPokerPredicate customPokerPredicate, CustomPokerFunction customPokerFunction, CustomScoreCalculator customScoreCalculator) {
        if (customPokerPredicate.test(this)) {
            return customPokerFunction.apply(this,customScoreCalculator);
        } else {
            return getCombination(customPokerPredicate.getNext(), customPokerFunction.getNext(),customScoreCalculator.getNext());

        }
    }


}
