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
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.FOUR);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.NINE);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.JACK);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEVEN);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TEN);
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
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TWO);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.THREE);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.QUEEN);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEVEN);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TEN);
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
        Card card1 = new Card(LabelCard.PIQUE, NumberCard.TWO);
        Card card2 = new Card(LabelCard.PIQUE, NumberCard.JACK);
        Card card3 = new Card(LabelCard.PIQUE, NumberCard.THREE);
        Card card4 = new Card(LabelCard.PIQUE, NumberCard.FOUR);
        Card card5 = new Card(LabelCard.COEUR, NumberCard.FIVE);
        Card card6 = new Card(LabelCard.COEUR, NumberCard.FIVE);
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
        Card card1 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TEN);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.THREE);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.JACK);
        Card card6 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.QUEEN);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TWO);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // then
        boolean isStraight =  StraightPredicate.getInstance().test(hand);
        assertThat(isStraight , is(true));
    }
}
