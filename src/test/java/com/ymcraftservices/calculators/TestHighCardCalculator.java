package com.ymcraftservices.calculators;

import com.ymcraftservices.DataFactory;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestHighCardCalculator {

    @Test
    public void should_return_this_score_for_this_combination() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEVEN);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TWO);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.THREE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NINE);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TEN);
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // when
        Integer score = new HighCardCalculator().apply(hand).getStrenght();
        // 140000+13000+1000+90 +7
        assertThat(score , is(154097));


    }
}
