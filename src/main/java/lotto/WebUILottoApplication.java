package lotto;

import com.google.gson.Gson;
import lotto.controller.HistoryController;
import lotto.controller.LottoController;
import lotto.controller.ResultController;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();
        staticFiles.location("/templates");
        post("/api/money", LottoController::insertMoney, gson::toJson);
        post("/api/purchase", LottoController::doPurchase, gson::toJson);
        post("/api/winning", ResultController::getWinningLotto, gson::toJson);
        get("/api/result", ResultController::getResult, gson::toJson);
        post("/api/history", HistoryController::getHistory, gson::toJson);
    }
}
