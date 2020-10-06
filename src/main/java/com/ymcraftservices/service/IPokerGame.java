package com.ymcraftservices.service;

import com.ymcraftservices.model.Player;

import java.util.List;

public interface IPokerGame {

     List<Player> getWinners(List<Player> players);
}
