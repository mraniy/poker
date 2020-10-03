package com.ymcraftservices.model;

public enum Combination {

    HIGHCARD(100),
    PAIR(200),
    DOUBLEPAIR(300),
    BRELAN(400),
    STRAIGHT(500),
    FLUSH(600),
    FULLHOUSE(700),
    CARRE(800),
    KENTFLUSH(900),
    ROYALFLUSH(1000);

    private int score;

    public int getScore() {
        return score;
    }

    Combination(int score) {
        this.score = score;
    }


}
