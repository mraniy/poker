package com.ymcraftservices.service;

import com.ymcraftservices.model.Player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PokerGame {

    public List<Player> getWinner(List<Player> players) {
        Integer highScoreHands = players.stream()
                .map(player -> player.getHand().getScore())
                .max(Comparator.comparingInt(o2 -> o2))
                .orElseGet(() -> 0);
        return players.stream()
                .sorted(Comparator.comparing(o -> o.getHand().getScore()))
                .filter(player -> player.getHand().getScore().equals(highScoreHands))
                .collect(Collectors.toList());

    }


}
