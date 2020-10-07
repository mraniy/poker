package com.ymcraftservices.predicates;

import com.ymcraftservices.DataFactory;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class TestStraightFlush {

    @Test
    public void should_return_false_when_the_hand_is_not_a_straight_flush() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TEN);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.EIGHT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.JACK);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NINE);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TEN);
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // when
        StraightFlushPredicate straightFlushPredicate =  StraightFlushPredicate.getInstance();
        boolean isSraightFlush = straightFlushPredicate.test(hand);
        assertThat(isSraightFlush , is(false));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_straight_flush() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TEN);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.EIGHT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.JACK);
        Card card6 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.NINE);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TEN);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // when
        StraightFlushPredicate straightFlushPredicate =  StraightFlushPredicate.getInstance();
        boolean isStraightFlush = straightFlushPredicate.test(hand);
        // then
        assertThat(isStraightFlush, is(true));
    }

}
