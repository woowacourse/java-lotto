package lotto.controller;

import lotto.service.LottoResultService;
import lotto.service.LottoTicketsService;
import lotto.service.WinningLottoService;
import lottogame.domain.*;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
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
        req.session().attribute("round", LottoResultService.findPresentRound());
        model.put("price", price);
        model.put("rateOfLotto", lottoResult.getRateOfLotto(money));
        setMatchResult(model, lottoResult);
        return render(model, "lottoresult.html");
    }


    public static Object showSelectRoundPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = req.session().attribute("round");
        List<Integer> rounds = LottoResultService.findAllRound(round);
        if (rounds.size() == 0) {
            return render(model, "noselect");
        }
        model.put("rounds", rounds);
        return render(model, "selectround.html");
    }

    public static Object showResultOfRound(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(req.queryParams("selected-round"));
        LottoTickets lottoTickets = LottoTicketsService.findTicketsByRound(round);
        WinningLotto winningLotto = WinningLottoService.findWinningLottoByRound(round);
        LottoResult lottoResult = LottoResultService.findLottoResultByRound(round);
        int price = lottoTickets.numberOfLottos() * 1000;
        Money money = Money.generate(price);
        model.put("round", round);
        model.put("numberOfTicket", lottoTickets.numberOfLottos());
        model.put("lottos", lottoTickets.getLottos());
        model.put("winninglotto", winningLotto.getWinningLotto());
        model.put("bonus", winningLotto.getBonusNumber());
        model.put("price", price);
        model.put("rateOfLotto", lottoResult.getRateOfLotto(money));
        setMatchResult(model, lottoResult);
        return render(model, "resultofround.html");
    }

    private static void setMatchResult(Map<String, Object> model, LottoResult lottoResult) {
        model.put("first", LottoResultService.getResultOf(lottoResult, Rank.FIRST));
        model.put("second", LottoResultService.getResultOf(lottoResult, Rank.SECOND));
        model.put("third", LottoResultService.getResultOf(lottoResult, Rank.THIRD));
        model.put("fourth", LottoResultService.getResultOf(lottoResult, Rank.FOURTH));
        model.put("fifth", LottoResultService.getResultOf(lottoResult, Rank.FIFTH));
    }
}
