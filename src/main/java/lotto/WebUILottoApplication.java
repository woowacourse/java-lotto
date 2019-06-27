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

        get("/api/results", LottoWebController::retrieveAggregations, gson::toJson);
        get("/api/result/:id", LottoWebController::retrieveSingleAggregation, gson::toJson);
        post("/api/buy", LottoWebController::buyLotto, gson::toJson);
        post("/api/draw", LottoWebController::draw, gson::toJson);

        // api 루트가 아닌 경우에는 인덱스 페이지 반환(SPA)
        get("/*", LottoWebController::index);
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
