package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.DoublePair;
import com.ymcraftservices.testhands.service.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestPair {

    @Test
    public void should_return_false_when_the_combination_is_not_a_pair() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> notPair = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand doublePair = new Pair(notPair);
        // then
        assertThat(doublePair.verify() , is(false));

    }

    @Test
    public void should_return_true_when_the_combination_is_a_pair() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.QUATRE);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> doublePair = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand doublePairCombination = new Pair(doublePair);
        // then
        assertThat(doublePairCombination.verify() , is(true));
    }
}
