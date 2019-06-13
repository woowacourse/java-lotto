package lotto.controller;

import lotto.service.LottoResultService;
import lotto.service.LottoTicketsService;
import lotto.service.WinningLottoService;
import lottogame.domain.*;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottoResultController {
    public static Object showLottoResultPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = req.session().attribute("round");
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create(req.queryParams("winning-lotto")),
                Integer.parseInt(req.queryParams("bonus-number")));
        WinningLottoService.insertWinningLotto(round, winningLotto);
        LottoTickets lottoTickets = LottoTicketsService.findTicketsByRound(round);
        int price = lottoTickets.numberOfLottos() * 1000;
        Money money = Money.generate(price);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        LottoResultService.updateLottoResult(round, lottoResult);
        LottoResultService.insertNewLottoRound();
        model.put("price", price);
        model.put("rateOfLotto", lottoResult.getRateOfLotto(money));
        model.put("first", LottoResultService.getResultOf(lottoResult, Rank.FIRST));
        model.put("second", LottoResultService.getResultOf(lottoResult, Rank.SECOND));
        model.put("third", LottoResultService.getResultOf(lottoResult, Rank.THIRD));
        model.put("fourth", LottoResultService.getResultOf(lottoResult, Rank.FOURTH));
        model.put("fifth", LottoResultService.getResultOf(lottoResult, Rank.FIFTH));
        return render(model, "lottoresult.html");
    }
}
