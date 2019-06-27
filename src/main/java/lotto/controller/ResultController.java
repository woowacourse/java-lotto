package lotto.controller;

import lotto.StandardResponse;
import lotto.StatusResponse;
import lotto.service.ResultService;
import lotto.utils.JsonUtils;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ResultController {
    private static ResultService service;
    private static int INTERNAL_SERVER_ERROR_CODE = 500;

    static {
        service = new ResultService();
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
            tables.put("Result", service.getResult(round));
            tables.put("Lotto", service.getPurchasedLotto(round));
            tables.put("WinningLotto", service.getWinningLotto(round));
        } catch (Exception e) {
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return new StandardResponse(StatusResponse.ERROR, e.getMessage()).toJson();
        }
        return new StandardResponse(StatusResponse.SUCCESS, JsonUtils.toJsonTree(tables)).toJson();
    };

    public static Route responseRound = (request, response) -> {
        response.type("application/json");
        try {
            return new StandardResponse(StatusResponse.SUCCESS, JsonUtils.toJson(service.getMaxRound())).toJson();
        } catch (Exception e) {
            response.status(INTERNAL_SERVER_ERROR_CODE);
            return new StandardResponse(StatusResponse.ERROR, e.getMessage()).toJson();
        }
    };
}
