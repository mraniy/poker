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

public class TestFullHouseScoreCalculator {

    @Test
    public void should_return_score_when_full_house() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEVEN);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TWO);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TEN);
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        FullHouseCalculator fullHouseCalculator = new FullHouseCalculator();
        // when
        Integer strenght = fullHouseCalculator.apply(hand).getStrenght();
        // then
        // 14*10 + 7 = 147
        assertThat(strenght , is(147));
    }

    @Test
    public void should_return_score_when_full_house_of_three() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEVEN);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEVEN);
        Card card7 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TEN);
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        FullHouseCalculator fullHouseCalculator = new FullHouseCalculator();
        // when
        Integer strenght = fullHouseCalculator.apply(hand).getStrenght();
        // then
        // 14*10 + 7 = 147
        assertThat(strenght , is(147));
    }
}
