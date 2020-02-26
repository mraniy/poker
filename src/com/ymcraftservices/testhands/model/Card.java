package com.ymcraftservices.testhands.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {

    private LabelCard labelCard;

    private NumberCard numberCard;
}
