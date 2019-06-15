package lotto.controller;

import com.google.gson.Gson;
import lotto.StandardResponse;
import lotto.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ResultController {
    private static LottoService service;

    static {
        service = new LottoService();
    }

    public static Route serveResultPage = (Request request, Response response) -> {
        response.redirect("/result.html");
        return null;
    };

    public static Route responseResultRound = (request, response) -> {
        int round = Integer.valueOf(request.params(":round"));
        response.type("application/json");
        Map<String, Object> tables = new HashMap<>();
        try {
            tables.put("Result", new Gson().toJson(service.getResult(round)));
            tables.put("Lotto", new Gson().toJson(service.getPurchasedLotto(round)));
            tables.put("WinningLotto", new Gson().toJson(service.getWinningLotto(round)));
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(tables)));
    };

    public static Route responseRound = (request, response) -> {
        response.type("application/json");
        try {
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson().toJson(service.getMaxRound())));
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
    };
}
