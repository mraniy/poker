package com.ymcraftservices.predicates;

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

public class TestStraightPredicate {

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
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // then
        StraightPredicate straightPredicate =  StraightPredicate.getInstance();
        boolean isStraight = straightPredicate.test(hand);
        assertThat(isStraight , is(false));
    }

    @Test
    public void should_return_false_when_the_hand_is_not_a_straight_to_2() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.DEUX);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TROIS);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEPT);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // then
        StraightPredicate straightPredicate = StraightPredicate.getInstance();
        boolean isStraight = straightPredicate.test(hand);
        assertThat(isStraight , is(false));
    }


    @Test
    public void should_return_true_when_the_hand_is_a_straight_not_to_as() {
        // given
        Card card7 = new Card(LabelCard.TREFLE, NumberCard.AS);
        Card card1 = new Card(LabelCard.PIQUE, NumberCard.DEUX);
        Card card2 = new Card(LabelCard.PIQUE, NumberCard.VALLEE);
        Card card3 = new Card(LabelCard.PIQUE, NumberCard.TROIS);
        Card card4 = new Card(LabelCard.PIQUE, NumberCard.QUATRE);
        Card card5 = new Card(LabelCard.COEUR, NumberCard.CINQ);
        Card card6 = new Card(LabelCard.COEUR, NumberCard.CINQ);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // then
        StraightPredicate straightPredicate = StraightPredicate.getInstance();
        boolean isStraight = straightPredicate.test(hand);
        assertThat(isStraight , is(true));
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
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // then
        boolean isStraight =  StraightPredicate.getInstance().test(hand);
        assertThat(isStraight , is(true));
    }
}
