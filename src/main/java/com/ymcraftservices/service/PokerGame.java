package com.ymcraftservices.service;

import com.ymcraftservices.model.Combination;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class PokerGame implements IPokerGame{

    @Override
    public List<Player> getWinners(List<Player> players) {
        Integer highScoreHands = players.stream()
                .map(player -> player.getHand().getCombination())
                .max(Comparator.comparingInt(Combination::getScore))
                .map(Combination::getScore)
                .orElseGet(() -> 0);
        return players.stream()
                .sorted(Comparator.comparing(o -> o.getHand().getCombination().getScore()))
                .peek(player -> log.info(player.getFirstName().concat(" has ").concat(player.getHand().getCombination().getMessage())))
                .filter(player -> player.getHand().getCombination().getScore().equals(highScoreHands))
                .peek(player -> log.info(player.getFirstName().concat(" wins it with a ").concat(player.getHand().getCombination().getMessage())))
                .collect(Collectors.toList());
    }
}
