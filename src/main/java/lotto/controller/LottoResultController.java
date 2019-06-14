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
        LottoTickets lottoTickets = LottoTicketsService.findTicketsByRound(round);
        int price = getPrice(res, lottoTickets);
        Money money = Money.generate(price);
        LottoResult lottoResult = LottoResultGenerator.create(lottoTickets, winningLotto);

        updateResult(round, winningLotto, lottoResult);
        req.session().attribute("round", LottoResultService.findPresentRound());

        model.put("price", price);
        setMatchResult(model, lottoResult, money);
        return render(model, "lottoresult.html");
    }

    private static void updateResult(int round, WinningLotto winningLotto, LottoResult lottoResult) {
        WinningLottoService.insertWinningLotto(round, winningLotto);
        LottoResultService.updateLottoResult(round, lottoResult);
        LottoResultService.insertNewLottoRound();
    }


    public static Object showSelectRoundPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = req.session().attribute("round");
        List<Integer> rounds = LottoResultService.findAllRound(round);

        if (rounds.size() == 0) {
            return render(model, "noselect.html");
        }

        model.put("rounds", rounds);
        return render(model, "selectround.html");
    }

    public static Object showResultOfRound(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        Map<String, Object> objects = new HashMap<>();
        int round = Integer.parseInt(req.queryParams("selected-round"));

        objects.put("round", round);
        objects.put("lottoTickets", LottoTicketsService.findTicketsByRound(round));
        objects.put("winningLotto", WinningLottoService.findWinningLottoByRound(round));
        objects.put("lottoResult", LottoResultService.findLottoResultByRound(round));
        setResultOfRound(model, objects, res);

        return render(model, "resultofround.html");
    }

    private static void setResultOfRound(Map<String, Object> model, Map<String, Object> objects, Response res) {
        LottoTickets lottoTickets = (LottoTickets) objects.get("lottoTickets");
        int price = getPrice(res, lottoTickets);
        Money money = Money.generate(price);

        model.put("round", objects.get("round"));
        model.put("price", price);
        setLottoTickets(model, lottoTickets);
        setWinningLotto(model, (WinningLotto) objects.get("winningLotto"));
        setMatchResult(model, (LottoResult) objects.get("lottoResult"), money);
    }

    private static int getPrice(Response res, LottoTickets lottoTickets) {
        int price = lottoTickets.numberOfLottos() * 1000;

        if (price == 0) {
            res.redirect("nopurchaselotto.html");
        }

        return price;
    }

    private static void setWinningLotto(Map<String, Object> model, WinningLotto winningLotto) {
        model.put("winninglotto", winningLotto.getWinningLotto());
        model.put("bonus", winningLotto.getBonusNumber());
    }

    private static void setLottoTickets(Map<String, Object> model, LottoTickets lottoTickets) {
        model.put("numberOfTicket", lottoTickets.numberOfLottos());
        model.put("lottos", lottoTickets.getLottos());
    }

    private static void setMatchResult(Map<String, Object> model, LottoResult lottoResult, Money money) {
        model.put("first", LottoResultService.getResultOf(lottoResult, Rank.FIRST));
        model.put("second", LottoResultService.getResultOf(lottoResult, Rank.SECOND));
        model.put("third", LottoResultService.getResultOf(lottoResult, Rank.THIRD));
        model.put("fourth", LottoResultService.getResultOf(lottoResult, Rank.FOURTH));
        model.put("fifth", LottoResultService.getResultOf(lottoResult, Rank.FIFTH));
        model.put("rateOfLotto", lottoResult.getRateOfLotto(money));
    }
}
