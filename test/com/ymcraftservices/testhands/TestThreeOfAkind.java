package com.ymcraftservices.testhands;

import com.ymcraftservices.testhands.model.Card;
import com.ymcraftservices.testhands.model.Hand;
import com.ymcraftservices.testhands.model.LabelCard;
import com.ymcraftservices.testhands.model.NumberCard;
import com.ymcraftservices.testhands.service.ThreeOfAkind;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;

public class TestThreeOfAkind {

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
        List<Card> notThreeOfAKind = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand notThreeOfKind = new ThreeOfAkind(notThreeOfAKind);
        // then
        assertThat(notThreeOfKind.verify() , is(false));

    }

    @Test
    public void should_return_true_when_the_combination_is_a_three_of_a_kind() {
        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> notThreeOfAKind = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        Hand threeOfAkind = new ThreeOfAkind(notThreeOfAKind);
        // thent
        assertThat(threeOfAkind.verify() , is(true));
    }

    @Test
    public void should_return_three_of_ace_vallee_as_kicker()  {

        // given
        Card card1 = DataFactory.getCard(LabelCard.PIQUE, NumberCard.AS);
        Card card2 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.AS);
        Card card3 = DataFactory.getCard(LabelCard.TREFLE, NumberCard.SEPT);
        Card card4 = DataFactory.getCard(LabelCard.COEUR, NumberCard.AS);
        Card card5 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.VALLEE);
        Card card6 = DataFactory.getCard(LabelCard.COEUR, NumberCard.NEUF);
        Card card7 = DataFactory.getCard(LabelCard.CARREAU, NumberCard.DIX);
        // when
        List<Card> threeOfAKind = Arrays.asList(card1, card2, card3, card4, card5, card6, card7);
        ThreeOfAkind threeOfKind = new ThreeOfAkind(threeOfAKind);
        threeOfKind.setBestFiveCards();
        // then

        assertThat(threeOfKind.getBestFiveCards() , containsInAnyOrder(card1,card2,card4,card5,card7));
        assertThat(threeOfKind.getKicker(), is(NumberCard.VALLEE.getNumber()));

    }
}
