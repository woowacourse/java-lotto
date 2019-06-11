package lotto.service;

import com.google.gson.*;
import lotto.domain.Lotteries;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.autocreatelotto.DefaultAutoCreateLotto;
import lotto.domain.customcreatelotto.CustomCreateLotto;
import lotto.domain.customcreatelotto.DefaultCustomCreateCreateLotto;
import spark.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heebg
 * @version 1.0 2019-06-10
 */
public class CallRestApiService {
    private static Money money;
    private static Lotteries lotteries;

    public String lottoBuyCount(Request req) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(req.body());
        long reqMoney = jsonElement.getAsJsonObject().get("money").getAsLong();
        money = new Money(reqMoney);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", money.generateLottoBuyCount());
        jsonObject.addProperty("money", reqMoney);
        return new Gson().toJson(jsonObject);
    }

    public String detailLotteries(Request req) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(req.body());

        long manualCount = jsonElement.getAsJsonObject().get("manual_count").getAsLong();
        long autoCount = money.calculateAutoCount(manualCount);

        lotteries = generateLotteries(jsonElement, manualCount, autoCount);

        JsonObject jsonObject = generateResponseDetailLotteries(manualCount, autoCount, lotteries);
        return new Gson().toJson(jsonObject);
    }

    private JsonObject generateResponseDetailLotteries(long manualCount, long autoCount, Lotteries lotteries) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("lotteries", generateResponseLotteries(lotteries));
        jsonObject.addProperty("manual_count", manualCount);
        jsonObject.addProperty("auto_count", autoCount);
        return jsonObject;
    }

    private JsonArray generateResponseLotteries(Lotteries lotteries) {
        JsonArray jsonArray = new JsonArray();
        for (Lotto lotto : lotteries) {
            jsonArray.add(generateResponseLotto(lotto));
        }

        return jsonArray;
    }

    private JsonArray generateResponseLotto(Lotto lotto) {
        JsonArray jsonArray = new JsonArray();
        for (LottoNumber lottoNumber : lotto) {
            jsonArray.add(lottoNumber.toString());
        }
        return jsonArray;
    }

    private Lotteries generateLotteries(JsonElement jsonElement, long manualCount, long autoCount) {
        lotteries = generateManualLotteries(manualCount, jsonElement);
        lotteries.addAutoLotteries(autoCount, new DefaultAutoCreateLotto());
        return lotteries;
    }

    private Lotteries generateManualLotteries(long manualCount, JsonElement jsonElement) {
        lotteries = new Lotteries();
        if (manualCount != 0) {
            JsonArray array = jsonElement.getAsJsonObject().get("lotteries").getAsJsonArray();
            lotteries = addManualLotteries(array);
        }
        return lotteries;
    }

    private Lotteries addManualLotteries(JsonArray array) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JsonArray lotto = array.get(i).getAsJsonArray();
            lottos.add(generateLotto(lotto));
        }
        System.out.println(array.get(0).getAsJsonArray().get(0).getAsInt());

        return new Lotteries(lottos);
    }

    private Lotto generateLotto(JsonArray lotto) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < lotto.size(); i++) {
            lottoNumbers.add(new LottoNumber(lotto.get(i).getAsInt()));
        }
        return Lotto.createLotto(lottoNumbers);
    }
}
