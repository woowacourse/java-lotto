package lotto;

import lotto.domain.BoughtLottos;
import lotto.domain.WinningNumber;
import lotto.service.LottoService;
import lotto.service.ResultService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) throws SQLException {
        port(8080);

        LottoService lottoService = new LottoService();
        ResultService resultService = new ResultService();
        int current = lottoService.getCurrentRound() + 1;

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        get("/result/:round", (req, res) -> {
            int targetRound = Integer.parseInt(req.params(":round"));
            Map<String, Object> model = new HashMap<>();

            model.put("lottoGame", resultService.findGameDataByRound(targetRound));
            model.put("lottos", resultService.findLottosByRound(targetRound));
            model.put("winningNumber", resultService.findWinningNumberByRound(targetRound));
            model.put("result", resultService.findResultByRound(targetRound));

            return render(model, "result.html");
        });

        post("/lotto", (req, res) -> {
            BoughtLottos boughtLottos = lottoService.generateBoughtLottos(current,
                    req.queryParams("buy-price"), req.queryParams("lotto-numbers"));
            WinningNumber winningNumber = lottoService.generateWinningNumber(current,
                    req.queryParams("winning-numbers"), req.queryParams("bonus"));
            lottoService.generateResult(current, boughtLottos, winningNumber);

            res.redirect("/result/" + current);
            return true;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
