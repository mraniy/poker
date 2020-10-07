package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestFullMessage {

    @Test
    public void should_return_this_message() {
        FullHouseMessage fullHouseMessage = new FullHouseMessage();
        String maxCard = NumberCard.KING.toString();
        String minCard = NumberCard.AS.toString();
        String message = fullHouseMessage.apply(Arrays.asList(maxCard, minCard));
        assertThat(message, is("Full House from KING To AS"));
    }
}
