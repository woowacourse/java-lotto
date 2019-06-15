package lotto.Controller;

import lotto.service.RoundService;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class RoundController {

    private static final RoundService roundService = new RoundService();

    public static final Route GET_MAX_ROUND = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int newRound = roundService.findMaxRound() + 1;
        model.put("newRound", newRound);
        return render(model, "/index.html");
    };

    public static final Route SAVE_PURCHASE_AMOUNT = (request, response) -> {
        Map<String, Object> model = new HashMap<>();
        int amount = Integer.parseInt(request.queryParams("amount"));
        roundService.addRound(amount);
        model.put("amount", amount);
        model.put("countOfPurchase", amount / 1000);
        return render(model, "manualLotto.html");
    };
}
