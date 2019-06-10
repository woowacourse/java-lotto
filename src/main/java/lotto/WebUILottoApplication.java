package lotto;

import com.google.gson.Gson;
import com.woowacourse.lotto.controller.LottoWebController;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Gson gson = new Gson();

        staticFiles.location("/public");

        // AJAX 요청 시 CORS 적용
        options("/*", WebUILottoApplication::cors);
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/*", LottoWebController::index);
        post("/api/buy-auto", LottoWebController::buyAutoLotto, gson::toJson);
        post("/api/buy-manual", LottoWebController::buyManualLotto, gson::toJson);
        post("/api/draw", LottoWebController::draw, gson::toJson);
        get("/api/result/:id", LottoWebController::retrieveSingleResult, gson::toJson);
    }

    private static String cors(Request request, Response response) {

        String accessControlRequestHeaders = request
            .headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers",
                accessControlRequestHeaders);
        }

        String accessControlRequestMethod = request
            .headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods",
                accessControlRequestMethod);
        }

        return "OK";
    }
}
