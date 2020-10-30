package com.ymcraftservices.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data
public class Pot {

    List<Player> players;

    Integer pot;
}
