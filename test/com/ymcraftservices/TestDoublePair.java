package com.ymcraftservices;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.service.DoublePair;
import com.ymcraftservices.model.Hand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

public class TestDoublePair {

    @Test
    public void should_return_false_when_the_combination_is_not_a_double_pair() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> notDoublePair = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand doublePair = new DoublePair(notDoublePair);
        // then
        assertThat(doublePair.verify() , is(false));

    }

    @Test
    public void should_return_true_when_the_combination_is_a_double_pair() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> doublePair = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand doublePairCombination = new DoublePair(doublePair);
        // then
        assertThat(doublePairCombination.verify() , is(true));
    }

    @Test
    public void should_return_double_pair_ace_vallee_dix_as_kicker() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.VALLEE);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> doublePair = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand doublePairCombination = new DoublePair(doublePair);
        doublePairCombination.setBestFiveCards();
        // then

        assertThat(doublePairCombination.getBestFiveCards() ,
                containsInAnyOrder(card1,card2,card5,card6,card7));
        assertThat(((DoublePair)doublePairCombination).getKicker(), is(10));
    }
}