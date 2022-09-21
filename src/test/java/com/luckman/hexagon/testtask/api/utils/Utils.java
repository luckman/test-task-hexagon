package com.luckman.hexagon.testtask.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;

import java.io.IOException;

public class Utils {
    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();

    public static <C> C extractFromResponse(Response response, Class<C> cClass) {
        try {
            return defaultObjectMapper.readValue(response.body().string(), cClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
