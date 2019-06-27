package lotto.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {
    private static JsonParser parser = new JsonParser();
    private static Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static JsonElement toJsonTree(Object object) {
        return gson.toJsonTree(object);
    }

    public static JsonObject parse(String content) {
        return parse(content);
    }
}
