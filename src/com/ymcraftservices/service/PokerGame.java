package com.ymcraftservices.service;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PokerGame {

    public List<Player> getWinner(List<Player> players) {
        Integer highScoreHands = players.stream()
                .map(player -> player.getHand().getCombination())
                .max(Comparator.comparingInt(Combination::getScore))
                .map(Combination::getScore)
                .orElseGet(() -> 0);
        return players.stream()
                .sorted(Comparator.comparing(o -> o.getHand().getCombination().getScore()))
                .peek(player -> System.out.println(player.getFirstName().concat(" has ").concat(player.getHand().getCombination().getMessage())))
                .filter(player -> player.getHand().getCombination().getScore().equals(highScoreHands))
                .collect(Collectors.toList());

    }


}
