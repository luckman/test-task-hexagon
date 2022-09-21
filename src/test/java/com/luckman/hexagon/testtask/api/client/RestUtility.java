package com.luckman.hexagon.testtask.api.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luckman.hexagon.testtask.api.utils.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RestUtility {
    private String baseUrl = "https://reqres.in/api";
    private OkHttpClient client = new OkHttpClient();

    public Response getForResponse(String url) {
        Request request = new Request.Builder()
                .url(baseUrl + url)
                .build();

        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <C> C getForObject(String url, Class<C> cClass) {
        return Utils.extractFromResponse(getForResponse(url), cClass);
    }
}
