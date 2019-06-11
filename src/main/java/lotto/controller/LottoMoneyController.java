package lotto.controller;

import lotto.application.lottomoney.LottoMoneyService;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottoMoneyController {
    public static final Route fetchLottoMoney = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        String purchasePrice = req.queryParams("input");
        model.put("lottoMoney", LottoMoneyService.makeLottoMoneyDto(purchasePrice));

        return render(model, "lotto-money.html");
    };

    private LottoMoneyController() {
    }
}
