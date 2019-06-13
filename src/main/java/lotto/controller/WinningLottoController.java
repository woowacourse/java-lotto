package lotto.controller;

import lotto.service.LottoTicketsService;
import lotto.service.WinningLottoService;
import lottogame.domain.*;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static lotto.WebUILottoApplication.render;
import static spark.Spark.*;

public class WinningLottoController {

    public static Object showWinningLottoInputPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "winninglottoinput.html");
    }

    private static Object showLottoResultPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = req.session().attribute("round");
        WinningLotto winningLotto = WinningLotto.generate(ManualLottoGenerator.create(req.queryParams("winning-lotto")),
                Integer.parseInt(req.queryParams("bonus-number")));
        WinningLottoService.insertWinningLotto(round, winningLotto);
        LottoTickets lottoTickets = LottoTicketsService.findTicketsByRound(round);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);
        model.put("lottoResult", lottoResult);
        return render(model, "lottoresult.html");
    }

    private static Optional<LottoTickets> getLottoTickets(Request req) {
        try {
            return Optional.of(req.session().attribute("lottoTickets"));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
