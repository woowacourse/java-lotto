package lotto;

import com.google.gson.Gson;
import com.woowacourse.lotto.controller.LottoWebController;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();

        staticFiles.location("/public");

        get("/*", LottoWebController::index);
        post("/api/buy-auto", LottoWebController::buyAutoLotto, gson::toJson);
        post("/api/buy-manual", LottoWebController::buyManualLotto, gson::toJson);
        post("/api/draw", LottoWebController::draw, gson::toJson);
    }
}
