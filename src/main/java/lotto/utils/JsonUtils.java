package lotto.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonUtils {
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static JsonElement toJsonTree(Object object) {
        return gson.toJsonTree(object);
    }
}