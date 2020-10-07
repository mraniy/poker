package com.ymcraftservices.message;

import java.util.List;
import java.util.function.Function;

public class BrelanMessage implements Function<List<String>, String> {
    @Override
    public String apply(List<String> params) {
        return String.format("THREE of kind of %s with %s as a kicker", params.get(0), params.get(1));
    }
}
