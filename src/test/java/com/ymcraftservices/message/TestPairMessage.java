package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestPairMessage {

    @Test
    public void should_return_this_message() {
        PairMessage pairMessage = new PairMessage();
        String pair = NumberCard.AS.toString();
        String kicker = NumberCard.KING.toString();
        String message = pairMessage.apply(Arrays.asList(pair, kicker));
        assertThat(message, is("Pair of AS with KING as a kicker"));
    }
}
