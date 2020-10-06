package com.ymcraftservices.calculators;

import com.ymcraftservices.DataFactory;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStraightCalculator {

    @Test
    public void should_return_this_score_for_this_hand() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TWO);
        Card card3 = DataFactory.getCard(LabelCard.COEUR, NumberCard.THREE);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.FOUR);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.FIVE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SIX);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        StraightCalculator straightCalculator = new StraightCalculator();
        // when
        Integer strenght = straightCalculator.apply(hand).getStrenght();
        // then
        // 14*200(brelan) + 12(kicker)
        assertThat(strenght , is(NumberCard.SIX.getNumber()));
    }
}
