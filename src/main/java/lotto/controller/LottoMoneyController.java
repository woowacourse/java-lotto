package lotto.controller;

import lotto.application.LottoSession;
import lotto.application.lottomoney.LottoMoneyService;
import lotto.domain.lottomoney.dto.LottoMoneyDto;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottoMoneyController {
    public static final Route fetchLottoMoney = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        String purchasePrice = req.queryParams("lottoMoney");
        LottoMoneyDto lottoMoneyDto = LottoMoneyService.makeLottoMoneyDto(purchasePrice);
        LottoSession.setNumOfLotto(lottoMoneyDto.getNumOfLotto());

        model.put("lottoMoney", lottoMoneyDto);
        return render(model, "lotto-money.html");
    };

    private LottoMoneyController() {
    }
}
