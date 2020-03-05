package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.FullHouse;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestFullHouse {

    @Test
    public void should_return_false_when_the_hand_is_not_a_full_house() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> notFullHouse = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand notAFullHouseCombination = new FullHouse(notFullHouse);
        // then
        assertThat(notAFullHouseCombination.verify() , is(false));
    }





    @Test
    public void should_return_true_when_the_hand_is_a_full_house_by_thee_two() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> fullHouse = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand fullHouseCombination = new FullHouse(fullHouse);
        // then
        assertThat(fullHouseCombination.verify() , is(true));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_full_house_by_three_three() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> fullHouse = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand fullHouseCombination = new FullHouse(fullHouse);
        // then
        assertThat(fullHouseCombination.verify() , is(true));
    }




    @Test
    public void should_return_full_house_of_ace_to_seven() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEPT);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> fullHouse = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand fullHouseCombination = new FullHouse(fullHouse);
        fullHouseCombination.setBestFiveCards();
        // then
        assertThat(((FullHouse)fullHouseCombination).getFrom() , is(1));
        assertThat(((FullHouse)fullHouseCombination).getTo(), is(7));
    }


    @Test
    public void should_return_full_house_of_king_to_vallee() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.COEUR, NumberCard.KING);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.KING);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> fullHouse = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand fullHouseCombination = new FullHouse(fullHouse);
        fullHouseCombination.setBestFiveCards();

        // then
        assertThat(((FullHouse)fullHouseCombination).getFrom() , is(1));
        assertThat(((FullHouse)fullHouseCombination).getTo(), is(13));
    }



    @Test
    public void should_return_full_seven_of_seven_to_as() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEPT);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> fullHouse = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand fullHouseCombination = new FullHouse(fullHouse);
        fullHouseCombination.setBestFiveCards();
        // then
        assertThat(((FullHouse)fullHouseCombination).getFrom() , is(7));
        assertThat(((FullHouse)fullHouseCombination).getTo(), is(1));
    }
}
