package com.ymcraftservices.message;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DoublePairMessage implements Function<List<String>, String> {

    private  final String message = "double pair of (%s) with %s as a kicker";

    @Override
    public String apply(List<String> params) {
        String joinedDoublePairs = Arrays.asList(params.get(0), params.get(1)).stream().collect(Collectors.joining(","));
        return String.format(message, joinedDoublePairs, params.get(2));
    }
}
