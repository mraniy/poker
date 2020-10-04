package com.ymcraftservices.model;

public enum CombinationScore {

    HIGHCARD(10000),
    PAIR(20000),
    DOUBLEPAIR(30000),
    BRELAN(40000),
    STRAIGHT(50000),
    FLUSH(60000),
    FULLHOUSE(70000),
    CARRE(80000),
    KENTFLUSH(90000),
    ROYALFLUSH(100000);

    private int score;

    public int getScore() {
        return score;
    }

    CombinationScore(int score) {
        this.score = score;
    }


}
