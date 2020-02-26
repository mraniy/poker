package com.ymcraftservices.testhands.model;

public enum LabelCard {

    TREFLE ("TREFLE"),
    PIQUE ("PIQUE"),
    CARREAU ("CARREAU"),
    COEUR ("COEUR");

    LabelCard(String label) {
        this.label = label;
    }

    private String label = "";


}
