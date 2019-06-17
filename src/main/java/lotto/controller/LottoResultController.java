package lotto.controller;

import lotto.application.lottoresult.LottoResultService;
import lotto.controller.util.JsonUtil;
import lotto.domain.lottoresult.dto.LottoStatisticsDTO;
import spark.Route;

public class LottoResultController {
    public static final String PATH_LOTTO_RESULT = "/lotto-result";
    public static final String PATH_LAST_LOTTO_RESULT = "/last-lotto-result";

    public static final Route fetchLottoResult = (req, res) -> {
        int round = LottoResultService.fetchLatestRoundNum();
        LottoStatisticsDTO lottoResult = LottoResultService.getLottoResult(round);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoResult);
    };

    public static final Route fetchLastLottoResult = (req, res) -> {
        String round = req.queryParams("last-round");
        LottoStatisticsDTO lottoResult = LottoResultService.getLottoResult(round);

        return JsonUtil.convertDtoToJsonStringWith(res, lottoResult);
    };

    private LottoResultController() {
    }
}
