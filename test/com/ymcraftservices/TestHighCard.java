package com.ymcraftservices;

import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.service.HighCard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class TestHighCard {

    @Test
    public void should_return_false_when_the_combination_is_not_a_high_card() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> highcard = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand highCardCombination = new HighCard(highcard);
        // then
        assertThat(highCardCombination.verify() , is(false));
    }

    @Test
    public void should_return_true_when_the_combination_is_a_high_card() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TROIS);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> highcard = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand highCardCombination = new HighCard(highcard);
        // then
        assertThat(highCardCombination.verify() , is(true));
    }

    @Test
    public void should_return_high_card_to_ace() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.TROIS);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> highcard = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand highCardCombination = new HighCard(highcard);
        highCardCombination.setBestFiveCards();

        // then
        assertThat(highCardCombination.getBestFiveCards() , contains(card3,card6,card7,card2,card1));
    }
}
