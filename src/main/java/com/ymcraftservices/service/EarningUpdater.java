package com.ymcraftservices.service;

import com.ymcraftservices.model.Player;
import com.ymcraftservices.model.Pot;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EarningUpdater {

    WinnerFinder winnerFinder = new WinnerFinder();

    public List<Player> updateEarnings(Pot pot) {
        List<Player> winners = winnerFinder.getWinners(pot.getPlayers());
        winners.forEach(player -> {
            player.setPotScore(player.getPotScore() + pot.getPot() / winners.size());
            log.info(new StringBuilder("player :")
                    .append(player.getFirstName())
                    .append( " ")
                    .append(player.getLastName())
                    .append(" ").append(" pot").append(" become ").append(player.getPotScore()).toString()
            );
        });
        return winners;
    }
    
}
