package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.Flush;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class TestFlush {

    @Test
    public void should_return_false_when_the_hand_is_not_a_flush() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> Flush = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand notAFlush = new Flush(Flush);
        // then
        assertThat(notAFlush.verify() , is(false));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_flush() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TROIS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card6 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.DEUX);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> flush = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand flushHand = new Flush(flush);
        // then
        assertThat(flushHand.verify() , is(true));
    }

    @Test
    public void should_return_flush_to_as()  {
        // given
        Card card1 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.TROIS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.SEPT);
        Card card5 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.KING);
        Card card6 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.DEUX);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> flush = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand flushHand = new Flush(flush);
        // then
        assertThat(flushHand.getBestFiveCards().getCards() , contains(card6, card2,card1,card5, card3));
    }
}
