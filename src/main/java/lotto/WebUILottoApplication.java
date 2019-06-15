package lotto;

import lotto.domain.*;
import lotto.domain.Number;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static LottoService lottoService = new LottoService();

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        post("/lotto", (req, res) -> {
            int money = Integer.parseInt(req.queryParams("money"));
            int manualRound = Integer.parseInt(req.queryParams("manualRound"));
            String manualNumbers = req.queryParams("manualNumbers");

            return render(lottoService.offerLottoInfo(money, manualRound, manualNumbers), "lotto.html");
        });

        post("/winLotto", (req, res) -> {
            int round = Integer.parseInt(req.queryParams("round"));
            String userLottoString = req.queryParams("userLotto");
            return render(lottoService.offerUserLottoInfo(round, userLottoString), "winNumber.html");
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            WinningLotto winningLotto = new WinningLotto(
                    Parser.parseWinningLotto(req.queryParams("winNumbers"))
                    , Number.of(Integer.parseInt(req.queryParams("bonusNumber")))
            );

//            Winners winners = lottoService.createWinners(winningLotto.makeRankResultList(), winningLotto);
//
//            model.put("winningLotto", winningLotto);
//            model.put("winners", winners);
//            model.put("winnersResult", lottoService.provideResultStatus(winners.getRankResult()));
//            model.put("returnRate", lottoService.offerReturnRate(winners.calculateResultRate(round)));

            return render(model, "result.html");
        });
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
