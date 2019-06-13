package lotto.controller;

import lotto.domain.WinPrize;
import lotto.service.LottoService;
import lotto.service.WinPrizeService;
import lotto.service.WinningLottoService;
import lotto.view.ResultFormat;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class RoundController {
    private final WinPrizeService winPrizeService;
    private final LottoService lottoService;
    private final WinningLottoService winningLottoService;

    public RoundController(final WinPrizeService winPrizeService, final LottoService lottoService, final WinningLottoService winningLottoService) {
        this.winPrizeService = winPrizeService;
        this.lottoService = lottoService;
        this.winningLottoService = winningLottoService;
    }

    public Object round(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(req.queryParams("round"));
        WinPrize winPrize = winPrizeService.getAllByRound(round);

        model.put("results", ResultFormat.format(winPrize));
        model.put("rateOfProfit", winPrize.getRateOfProfit());
        model.put("round", round);
        model.put("lottos", lottoService.getByRound(round));
        model.put("winningLotto", winningLottoService.findAllByRound(round));
        return render(model, "round.html");
    }

}
