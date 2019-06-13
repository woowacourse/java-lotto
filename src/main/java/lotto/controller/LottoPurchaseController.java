package lotto.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.StandardResponse;
import lotto.StatusResponse;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class LottoPurchaseController {
    private static LottoService service;

    static {
        service = new LottoService();
    }

    public static Route inputBudget = (Request request, Response response) -> {
        response.type("application/json");
        JsonElement jsonBody = new JsonParser().parse(request.body());
        Map<String, Object> model = new HashMap<>();
        try {
            service.makeBuyer(jsonBody.getAsInt());
            model.put("maxManualCount", service.calculateMaxManualCount(jsonBody.getAsInt()));
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(model)));
    };

    public static Route serveInputPage = (Request request, Response response) -> {
        response.redirect("/input.html");
        return null;
    };

    public static Route inputManualLotto = (request, response) -> {
        response.type("application/json");
        String[] temps = request.queryMap("manualLottos").values();
        try {
            service.makeManualLotto(temps);
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };

    public static Route inputAutoLotto = (request, response) -> {
        response.type("application/json");
        try {
            service.makeAutoLotto();
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };

    public static Route registerLotto = (request, response) -> {
        try {
            service.registerLotto();
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };

    public static Route showLotto = (request, response) -> {
        response.type("application/json");
        Map<String, Object> model = new HashMap<>();
        try {
            model.put("lotto", service.showLottoInfos());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(model)));
    };
}