package com.ymcraftservices.predicates;

import com.ymcraftservices.model.Hand;

import java.util.List;
import java.util.stream.Collectors;


public class HighCardPredicate implements CustomPokerPredicate{

    private static HighCardPredicate instance;

    public static HighCardPredicate getInstance(){
        if (instance == null) {
            instance = new HighCardPredicate();
        }
        return instance;
    }

    private HighCardPredicate() {

    }



    @Override
    public boolean test(Hand hand) {
        List<Long> listOcuurences = hand.getAllCards()
                .stream()
                .collect(Collectors.groupingBy(card -> card.getNumberCard(), Collectors.counting()))
                .values()
                .stream()
                .distinct()
                .collect(Collectors.toList());
        return listOcuurences.size() == 1 && listOcuurences.get(0).equals(1L);

    }

    @Override
    public CustomPokerPredicate getNext() {
        return null;
    }
}
