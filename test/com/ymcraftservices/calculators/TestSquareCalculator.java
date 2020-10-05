package com.ymcraftservices.calculators;

import com.ymcraftservices.DataFactory;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestSquareCalculator {

    @Test
    public void should_return_this_score() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.VALLEE);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        Integer score = new SquareCalculator().apply(hand).getStrenght();
        // 14*10 +12
        assertThat(score, is(152));

    }
}
