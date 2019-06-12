package lotto.controller;

import lotto.application.LottoSession;
import lotto.application.lottomoney.LottoMoneyService;
import lotto.domain.lottomoney.dto.LottoMoneyDto;
import spark.Route;

public class LottoMoneyController {
    public static final Route fetchLottoMoney = (req, res) -> {
        String purchasePrice = req.queryParams("lottoMoney");
        LottoMoneyDto lottoMoneyDto = LottoMoneyService.makeLottoMoneyDto(purchasePrice);
        LottoSession.setNumOfLotto(lottoMoneyDto.getNumOfLotto());

        return JsonUtil.convertDtoToJsonStringWith(res, lottoMoneyDto);
    };

    private LottoMoneyController() {
    }
}
