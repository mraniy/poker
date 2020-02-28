package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.Flush;
import com.ymcraftservices.testhands.service.RoyalFlush;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestHand {

    @Test
    public void should_return_flush_as_a_hand() {
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
        Hand flushHand = new RoyalFlush(flush);
        Hand hand = flushHand.determineHand();

        // then
        assertThat((hand instanceof Flush), is(true));
    }
}
