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
import static org.hamcrest.core.Is.is;

public class TestFullHousePredicate {

    @Test
    public void should_return_false_when_the_hand_is_not_a_full_house() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.QUEEN);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.JACK);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NINE);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TEN);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // when
        FullHousePredicate fullHousePredicate =  FullHousePredicate.getInstance();
        boolean isFullHouse = fullHousePredicate.test(hand);
        assertThat(isFullHouse , is(false));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_full_house_by_thee_two() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEVEN);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEVEN);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEVEN);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.TWO);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TEN);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        // when
        FullHousePredicate fullHousePredicate =  FullHousePredicate.getInstance();
        boolean isFullHouse = fullHousePredicate.test(hand);
        assertThat(isFullHouse , is(true));
    }

    @Test
    public void should_be_full_house_with_two_three_of_kind() {
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
        // when
        boolean isFullHouse = FullHousePredicate.getInstance().test(hand);
        // then
        assertThat(isFullHouse, is(true));
    }

}
