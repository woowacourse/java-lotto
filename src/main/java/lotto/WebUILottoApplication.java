package lotto;

import com.google.gson.Gson;
import lotto.controller.LottoController;
import lotto.controller.ResultController;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/templates");
        post("/api/money", LottoController::insertMoney, gson::toJson);
        post("/api/purchase", LottoController::doPurchase, gson::toJson);
        post("/api/winning", ResultController::getWinningLotto,gson::toJson);
        post("/api/result", ResultController::getResult,gson::toJson);
//        post("/api/history", ResultController::getHistory,gson::toJson);
    }
}
