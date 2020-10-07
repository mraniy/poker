package com.ymcraftservices.message;

import com.ymcraftservices.model.NumberCard;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestFlushMessage {

    @Test
    public void should_return_this_message() {
        String flushMessage = new FlushMessage().apply(Arrays.asList(NumberCard.AS.toString()));
        assertThat(flushMessage , is("Flush to AS"));
    }
}
