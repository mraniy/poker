package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestHighCardMessage {

    @Test
    public void should_return_this_message() {
        HighCardMessage highCardMessage = new HighCardMessage();
        String highCard = NumberCard.KING.toString();
        String message = highCardMessage.apply(Arrays.asList(highCard));
        assertThat(message, is("highCard to KING"));
    }
}
