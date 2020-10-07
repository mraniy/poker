package com.ymcraftservices.message;

import java.util.List;
import java.util.function.Function;

public class RoyalFlushMessage implements Function<List<String>, String> {

    private final String format="THREE of kind of %s with %s as a kicker";

    @Override
    public String apply(List<String> params) {
        return String.format(format, params.get(0), params.get(1));
    }
}
