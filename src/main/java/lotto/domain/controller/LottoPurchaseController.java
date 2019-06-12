package lotto.domain.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lotto.StandardResponse;
import lotto.StatusResponse;
import lotto.domain.buyer.Budget;
import lotto.domain.buyer.LottoBuyer;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoType;
import lotto.utils.LottoNoParser;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.*;
import java.util.stream.Collectors;

public class LottoPurchaseController {
    public static Route serveInputPage = (Request request, Response response) -> {
        response.redirect("/input.html");
        return null;
    };
    private static LottoBuyer lottoBuyer;
    public static Route inputBudget = (Request request, Response response) -> {
        response.type("application/json");
        JsonElement jsonBody = new JsonParser().parse(request.body());
        Map<String, Object> model = new HashMap<>();
        try {
            lottoBuyer = new LottoBuyer(new Budget(jsonBody.getAsInt()));
            model.put("maxManualCount", jsonBody.getAsInt() / Lotto.LOTTO_PRICE);
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(model)));
    };

    public static Route inputManualLotto = (request, response) -> {
        response.type("application/json");
        String[] temps = request.queryMap("manualLottos").values();
        List<Lotto> lottos;
        try {
            lottos = Arrays.stream(temps)
                    .map(LottoNoParser::parseToLottoNos)
                    .map(Lotto::of).collect(Collectors.toList());
            lottoBuyer.buyManualLotto(lottos);
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };

    public static Route inputAutoLotto = (request, response) -> {
        response.type("application/json");
        try {
            lottoBuyer.buyAutoLotto();
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, e.getMessage()));
        }
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
    };

    public static Route showLotto = (request, response) -> {
        response.type("application/json");
        List<String> lottoMsg = new ArrayList<>();
        lottoMsg.add("수동으로 " + lottoBuyer.getCountOfLottoMatch(LottoType.MANUAL) + "개, "
                + "자동으로 " + lottoBuyer.getCountOfLottoMatch(LottoType.AUTOMATIC) + "개를 구매하셨습니다.");
        lottoMsg.addAll(lottoBuyer.showLottos());
        Map<String, Object> model = new HashMap<>();
        model.put("lotto", lottoMsg);
        return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(model)));
    };
}