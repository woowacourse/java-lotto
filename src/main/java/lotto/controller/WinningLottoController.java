package lotto.controller;

import lotto.domain.WinningLotto;
import lotto.service.WinningLottoService;
import lotto.service.WinningLottoTranslator;
import lotto.view.WebView;
import spark.Request;
import spark.Response;

import java.util.Map;

public class WinningLottoController {

    private WinningLottoController() {
    }

    public static Map<String, Object> winningLotto(Request request, Response response) {
        WinningLottoTranslator translator = winningLottoPresentation(request);
        int round = Integer.parseInt(request.queryParams("gameId"));
        WinningLotto winningLotto = WinningLottoService.insertWinningLotto(round, translator.getNumbers(), translator.getBonus());
        return WebView.winningLottoJson(winningLotto);
    }

    private static WinningLottoTranslator winningLottoPresentation(Request request) {
        return new WinningLottoTranslator(request.queryParams("numbers"), request.queryParams("bonus"));
    }
}
