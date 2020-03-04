package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.RoyalFlush;
import com.ymcraftservices.testhands.service.Straight;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class TestStraight {

    @Test
    public void should_return_false_when_the_hand_is_not_a_straight() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.QUATRE);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.NEUF);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEPT);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> notStraight = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand notStraightCombination = new Straight(notStraight);
        // then
        assertThat(notStraightCombination.verify(), is(false));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_straight_not_to_as() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TROIS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.HUIT);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        // when
        List<Card> straightCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand straightCombination = new Straight(straightCards);
        // then
        assertThat(straightCombination.verify(), is(true));
    }


    @Test
    public void should_return_straight_to_valee() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TROIS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.HUIT);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        // when
        List<Card> straightCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand straightCombination = new Straight(straightCards);
        // then
        assertThat(straightCombination.getBestFiveCards().getCards(), contains(card3,card4,card6,card1,card5));
    }

    @Test
    public void should_return_straight_to_as() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TROIS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        // when
        List<Card> straightCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand straightCombination = new Straight(straightCards);
        // then
        assertThat(straightCombination.getBestFiveCards().getCards(), contains(card1,card5,card6,card3,card4));
    }



}
