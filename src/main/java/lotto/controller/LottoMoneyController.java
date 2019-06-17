package lotto.controller;

import lotto.application.lottomoney.LottoMoneyService;
import lotto.controller.util.JsonUtil;
import lotto.domain.lottomoney.dto.LottoMoneyDTO;
import spark.Route;

public class LottoMoneyController {
    public static final Route fetchLottoMoney = (req, res) -> {
        String purchasePrice = req.queryParams("lottoMoney");
        LottoMoneyDTO lottoMoneyDto = LottoMoneyService.makeLottoMoneyDto(purchasePrice);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoMoneyDto);
    };

    private LottoMoneyController() {
    }
}
