package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestStraightFlushMessage {

    @Test
    public void should_return_this_message() {
        String straightFlushMessage = new StraightFlushMessage().apply(Arrays.asList(NumberCard.KING.toString()));
        assertThat(straightFlushMessage , is("Straight Flush to KING"));
    }
}
