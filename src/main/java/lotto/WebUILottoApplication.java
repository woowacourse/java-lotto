package lotto;

import lotto.service.RoundService;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int WINNING_LOTTO = 0;
    private static final int LOTTOS = 1;
    private static final int STATISTICS = 2;

    private static Map<String, Object> model;
    private static String round;
    private static LottoService lottoService;

    public static void main(String[] args) {
        port(8080);
        externalStaticFileLocation("/templates");

        get("/", (req, res) -> {
            round = new RoundService().searchRound();
            model = new HashMap<>();

            lottoService = new LottoService(round);

            model.put("round", round);

            return render(model, "lottobuyfrom.html");
        });

        post("/purchase", (req, res) -> {
            model = new HashMap<>();
            String money = req.queryParams("money");
            String[] customs = req.queryParamsValues("custom") != null ? req.queryParamsValues("custom") : new String[0];

            lottoService.buyLotto(money, customs);

            model.put("money", money);

            return render(model, "winningLotto.html");
        });

        post("/matchLotto", (req, res) -> {
            model = new HashMap<>();

            lottoService.enterWinningLotto(req.queryParams("winningNumbers"), req.queryParams("bonusNumber"));

            List<Object> result = lottoService.draw();

            model.put("winningLotto", result.get(WINNING_LOTTO));
            model.put("lottos", result.get(LOTTOS));
            model.put("statistics", result.get(STATISTICS));

            return render(model, "result.html");
        });

        post("/roundresult", (req, res) -> {
            model = new HashMap<>();
            String roundChoice = req.queryParams("roundchoice");
            List<Object> roundResult = lottoService.searchRound(roundChoice);

            model.put("winningLotto", roundResult.get(WINNING_LOTTO));
            model.put("lottos", roundResult.get(LOTTOS));
            model.put("statistics", roundResult.get(STATISTICS));

            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
