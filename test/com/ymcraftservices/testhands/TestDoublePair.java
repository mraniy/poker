package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.DoublePair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestDoublePair {

    @Test
    public void should_return_false_when_the_combination_is_not_a_three_of_a_kind() {
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
    public void should_return_true_when_the_combination_is_a_three_of_a_kind() {
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
}
