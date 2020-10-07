package com.ymcraftservices.message;

import java.util.List;
import java.util.function.Function;

public class HighCardMessage implements Function<List<String>, String> {

    private String format = "highCard to %s";

    @Override
    public String apply(List<String> params) {
        return String.format(format, params.get(0));
    }
}
