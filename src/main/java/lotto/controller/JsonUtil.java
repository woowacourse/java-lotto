package lotto.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Response;

public class JsonUtil {
    public static String convertDtoToJsonStringWith(Response res, Object dto) {
        setResponseTypeForJson(res);
        return convertDtoToJsonString(dto);
    }

    public static void setResponseTypeForJson(Response res) {
        res.type("application/json");
    }

    private static String convertDtoToJsonString(Object dto) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(dto);
    }
}
