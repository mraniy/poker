package com.ymcraftservices.model;

public enum CombinationScore {

    HIGHCARD(1000000),
    PAIR(2000000),
    DOUBLEPAIR(3000000),
    BRELAN(4000000),
    STRAIGHT(5000000),
    FLUSH(6000000),
    FULLHOUSE(7000000),
    CARRE(8000000),
    KENTFLUSH(9000000),
    ROYALFLUSH(10000000);

    private int score;

    public int getScore() {
        return score;
    }

    CombinationScore(int score) {
        this.score = score;
    }


}
