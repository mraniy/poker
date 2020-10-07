package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestSquareMessage {

    @Test
    public void should_return_this_message() {

        SquareMessage squareMessage = new SquareMessage();
        String square = NumberCard.AS.toString();
        String kicker = NumberCard.KING.toString();

        String message = squareMessage.apply(Arrays.asList(square, kicker));
        assertThat(message, is("Square of AS with KING as a kicker"));
    }
}
