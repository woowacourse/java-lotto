package lotto.controller;

import lotto.domain.LottoResult;
import lotto.service.LottoResultService;
import lotto.view.WebView;
import spark.Request;
import spark.Response;

import java.util.Map;

public class LottoResultController {

    private LottoResultController() {
    }

    public static Map<String, Object> lottoResult(Request request, Response response) {
        int round = Integer.parseInt(request.queryParams("gameId"));
        LottoResult lottoResult = LottoResultService.insertLottoResult(round);
        return WebView.lottoResultJson(lottoResult);
    }
}
