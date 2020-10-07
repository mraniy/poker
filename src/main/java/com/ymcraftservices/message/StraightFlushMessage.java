package com.ymcraftservices.message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StraightFlushMessage implements Function<List<String>, String> {

    private final String format="Straight Flush to %s";

    @Override
    public String apply(List<String> params) {
        return String.format(format, params.get(0));
    }
}
