package com.ymcraftservices.message;

import java.util.List;
import java.util.function.Function;

public class FlushMessage implements Function<List<String>, String> {

    private final String flushMessage= "Flush to %s";

    @Override
    public String apply(List<String> params) {
        return String.format(flushMessage, params.get(0));
    }
}
