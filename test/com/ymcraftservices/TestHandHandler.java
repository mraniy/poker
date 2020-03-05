package com.ymcraftservices;

import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.service.DoublePair;
import com.ymcraftservices.service.RoyalFlush;
import com.ymcraftservices.service.FullHouse;
import com.ymcraftservices.service.HandHandler;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestHandHandler {

    @Test
    public void should_determine_the_double_pair_hand() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand hand = HandHandler.determine(cards);
        // then
        assertThat(hand instanceof DoublePair, is(true));
    }

    @Test
    public void should_determine_the_royal_flush_hand() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.VALLEE);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.DIX);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand hand = HandHandler.determine(cards);
        // then
        assertThat(hand instanceof RoyalFlush, is(true));
    }

    @Test
    public void should_determine_the_full_house_hand() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.VALLEE);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> cards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand hand = HandHandler.determine(cards);
        // then
        assertThat(hand instanceof FullHouse, is(true));
    }
}
