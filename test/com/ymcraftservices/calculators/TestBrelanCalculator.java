package com.ymcraftservices.calculators;

import com.ymcraftservices.DataFactory;
import com.ymcraftservices.model.Card;
import com.ymcraftservices.model.Hand;
import com.ymcraftservices.model.LabelCard;
import com.ymcraftservices.model.NumberCard;
import com.ymcraftservices.scorecalculators.BrelanCalculator;
import com.ymcraftservices.scorecalculators.FullHouseCalculator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestBrelanCalculator {

    @Test
    public void should_return_score_when_brelan() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DEUX);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        List<Card> playercards = Arrays.asList(card3, card4, card5, card6, card7);
        List<Card> potcards = Arrays.asList(card1, card2);
        Hand hand = new Hand(potcards, playercards);
        BrelanCalculator brelanCalculator = new BrelanCalculator();
        // when
        Integer strenght = brelanCalculator.apply(hand);
        // then
        // 14*100(brelan) + 12*10 + 10
        assertThat(strenght , is(1530));
    }
}
