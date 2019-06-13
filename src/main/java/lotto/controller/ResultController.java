package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.WinPrize;
import lotto.domain.WinningLotto;
import lotto.service.LottoHelper;
import lotto.service.LottoService;
import lotto.service.WinPrizeService;
import lotto.service.WinningLottoService;
import lotto.view.ResultFormat;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ResultController {
    private final WinPrizeService winPrizeService;
    private final WinningLottoService winningLottoService;
    private final LottoService lottoService;

    public ResultController(final WinPrizeService winPrizeService, final WinningLottoService winningLottoService, final LottoService lottoService) {
        this.winPrizeService = winPrizeService;
        this.winningLottoService = winningLottoService;
        this.lottoService = lottoService;
    }

    public Object doPostResult(Request req, Response res) throws SQLException {
        String lotto = req.queryParams("winningLotto");
        int bonusNo = Integer.parseInt(req.queryParams("bonusNo"));
        int round = Integer.parseInt(req.queryParams("round"));
        WinningLotto winningLotto = LottoHelper.generateWinningLotto(lotto, bonusNo);

        winningLottoService.save(round, winningLotto);
        saveWinPrize(round, winningLotto);

        res.redirect("result?round=" + round);
        return null;
    }

    private void saveWinPrize(final int round, final WinningLotto winningLotto) throws SQLException {
        List<Lotto> userLottos = lottoService.getByRound(round);
        WinPrize winPrize = LottoHelper.generateWinPrize(userLottos, winningLotto);
        winPrizeService.save(winPrize, round);
    }

    public Object doGetResult(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(req.queryParams("round"));

        WinPrize winPrize = winPrizeService.getAllByRound(round);
        List<String> results = ResultFormat.format(winPrize);
        model.put("results", results);
        model.put("rateOfProfit", winPrize.getRateOfProfit());
        return render(model, "result.html");
    }
}
