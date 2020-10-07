package com.ymcraftservices.message;

import java.util.List;
import java.util.function.Function;

public class PairMessage implements Function<List<String>, String> {

    private final String pairMessage = "Pair of %s with %s as a kicker";
    @Override
    public String apply(List<String> params) {
        return String.format(pairMessage, params.get(0), params.get(1));
    }
}
