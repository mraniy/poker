package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestBrelanMessage {

    @Test
    public void should_return_this_message() {
        BrelanMessage brelanMessage = new BrelanMessage();
        String threeOfKind = NumberCard.AS.toString();
        String kicker = NumberCard.KING.toString();
        String message = brelanMessage.apply(Arrays.asList(threeOfKind, kicker));
        assertThat(message, is("THREE of kind of AS with KING as a kicker"));
    }
}
