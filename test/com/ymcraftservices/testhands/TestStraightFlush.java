package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.StraightFlush;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStraightFlush {

    @Test
    public void should_return_false_when_the_hand_is_not_a_straight_flush() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.HUIT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> notStraightFlushCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand notStraightFlushCombination = new StraightFlush(notStraightFlushCards);
        // then
        assertThat(notStraightFlushCombination.verify() , is(false));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_straight_flush() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.HUIT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> straightFlushCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand straightFlushCombination = new StraightFlush(straightFlushCards);
        // then
        assertThat(straightFlushCombination.verify() , is(true));
    }
}
