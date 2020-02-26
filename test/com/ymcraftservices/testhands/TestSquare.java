package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.RoyalFlush;
import com.ymcraftservices.testhands.service.Square;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestSquare {

    @Test
    public void should_return_false_when_the_hand_is_not_a_square() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.VALLEE);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> notSquareCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand notASquareCombination = new Square(notSquareCards);
        // then
        assertThat(notASquareCombination.verify() , is(false));
    }

    @Test
    public void should_return_true_when_the_hand_is_a_square() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card4 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DAME);
        Card card5 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.VALLEE);
        Card card7 = DataFactory.getCard(LabelCard.COEUR, NumberCard.DIX);
        // when
        List<Card> squareCards = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand squareCombination = new Square(squareCards);
        // then
        assertThat(squareCombination.verify() , is(true));
    }


}
