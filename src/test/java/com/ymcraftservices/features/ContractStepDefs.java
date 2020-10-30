package com.ymcraftservices.features;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ContractStepDefs {

    default List<Card> retrieveCards(List<List<String>> cards) {
        return cards.subList(1, cards.size())
                .stream()
                .map(strings -> new Card(LabelCard.valueOf(strings.get(0)), NumberCard.valueOf(strings.get(1))))
                .collect(Collectors.toList());
    }

    default Integer retrieveEarning(List<List<String>> earningsByPlayer, Player player) {


        List<String> playerAndEarning = earningsByPlayer.subList(1, earningsByPlayer.size())
                .stream()
                .filter(strings -> isTheCorrespondingPlayer(player, strings))
                .findFirst()
                .orElseGet(() -> new ArrayList<>());
        return Integer.parseInt(playerAndEarning.get(1));
    }

    default boolean isTheCorrespondingPlayer(Player player, List<String> strings) {
        return strings.get(0).split(" ")[0].trim().equals(player.getFirstName())
                &&
                strings.get(0).split(" ")[1].trim().equals(player.getLastName());
    }
}
