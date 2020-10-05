package com.ymcraftservices.model;


import com.ymcraftservices.calculators.CustomScoreCalculator;
import com.ymcraftservices.calculators.RoyalFlushCalculator;
import com.ymcraftservices.predicates.CustomPokerPredicate;
import com.ymcraftservices.predicates.RoyalFlushPredicate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
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

    public Combination getCombination() {
        RoyalFlushPredicate highLevelPredicate =  RoyalFlushPredicate.getInstance();
        RoyalFlushCalculator highLevelCalculator = new RoyalFlushCalculator();
        return getCombination(highLevelPredicate, highLevelCalculator);
    }



    private Combination getCombination(CustomPokerPredicate customPokerPredicate, CustomScoreCalculator customScoreCalculator) {
        if (customPokerPredicate.test(this)) {
            return customScoreCalculator.apply(this);
        } else {
            return getCombination(customPokerPredicate.getNext(), customScoreCalculator.getNext());

        }
    }


}
