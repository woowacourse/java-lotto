package lotto.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lotto.domain.Money;
import spark.Request;

/**
 * @author heebg
 * @version 1.0 2019-06-10
 */
public class CallRestApiService {
    public static String lottoBuyCount(Request req) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(req.body());
        int reqMoney = jsonElement.getAsJsonObject().get("money").getAsInt();
        Money money = new Money(reqMoney);
        System.out.println(money.generateLottoBuyCount());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", money.generateLottoBuyCount());
        jsonObject.addProperty("money", reqMoney);
        return new Gson().toJson(jsonObject);
    }
}
