package com.ymcraftservices.testhands.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Card {



    private LabelCard labelCard;

    private NumberCard numberCard;
}
