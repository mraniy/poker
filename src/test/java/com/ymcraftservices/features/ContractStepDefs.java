package com.ymcraftservices.features;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;

import java.util.List;
import java.util.stream.Collectors;

public interface ContractStepDefs {

    default   List<Card> retrieveCards(List<List<String>> cards) {
        return cards.subList(1, cards.size())
                .stream()
                .map(strings -> new Card(LabelCard.valueOf(strings.get(0)), NumberCard.valueOf(strings.get(1))))
                .collect(Collectors.toList());
    }
}
