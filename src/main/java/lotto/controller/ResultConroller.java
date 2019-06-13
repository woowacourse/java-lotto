package lotto.controller;

import com.google.gson.Gson;
import lotto.StandardResponse;
import lotto.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ResultConroller {
    public static Route serveResultPage = (Request request, Response response) -> {
        response.redirect("/result.html");
        return null;
    };
    private static LottoService service;
    public static Route responseResult = (request, response) -> {
        response.type("application/json");
        Map<String, Object> tables = new HashMap<>();
        try {
            tables.put("Result", new Gson().toJson(service.getResults()));
            tables.put("Lotto", new Gson().toJson(service.getPurchasedLotto()));
            tables.put("WinningLotto", new Gson().toJson(service.getWinningLotto()));
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(tables)));
    };

    static {
        service = new LottoService();
    }
}
