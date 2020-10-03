package com.ymcraftservices.model;


import com.ymcraftservices.functions.CustomPokerFuncion;
import com.ymcraftservices.functions.HighCardFunction;
import com.ymcraftservices.functions.RoyalFlushFunction;
import com.ymcraftservices.predicates.CustomPokerPredicate;
import com.ymcraftservices.predicates.HighCardPredicate;
import com.ymcraftservices.predicates.RoyalFlushPredicate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
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
        return getCombination(highLevelPredicate, highLevelFunction).getScore();
    }

    private Combination getCombination(CustomPokerPredicate customPokerPredicate, CustomPokerFuncion customPokerFuncion) {
        if (customPokerPredicate.test(this)) {
            return customPokerFuncion.apply(this);
        } else {
            return getCombination(customPokerPredicate.getNext(), customPokerFuncion.getNext());

        }
    }

    Map<NumberCard, Long> getCardsAndTheirOccurences(List<Card> cards) {
        return cards
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()));
    }

}
