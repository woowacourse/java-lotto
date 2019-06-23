package lotto;

import lotto.service.BuyingService;
import lotto.service.LottoService;
import lotto.service.RoundService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static BuyingService buyingService;
    private static LottoService lottoService;


    public static void main(String[] args) {
        port(8080);
        externalStaticFileLocation("/templates");

        get("/", (req, res) -> {
            req.session(true);
            Map<String, Object> model = new HashMap<>();
            String round = new RoundService().searchRound();

            req.session().attribute("round", round);

            model.put("round", round);

            return render(model, "lottobuyfrom.html");
        });

        post("/purchase", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String round = req.session().attribute("round");
            String money = req.queryParams("money");
            String[] customs = req.queryParamsValues("custom");

            buyingService = new BuyingService();
            buyingService.buyLotto(round, money, customs);

            model.put("round", round);

            return render(model, "winningLotto.html");
        });

        post("/matchLotto", (req, res) -> {
            Map<String, Object> model;

            String round = req.session().attribute("round");
            String winningNumbers = req.queryParams("winningNumbers");
            String bonusNumber = req.queryParams("bonusNumber");

            lottoService = new LottoService();
            lottoService.enterWinningLotto(round, winningNumbers, bonusNumber);
            lottoService.draw(round);

            model = lottoService.searchRound(round);

            return render(model, "result.html");
        });

        post("/roundresult", (req, res) -> {
            Map<String, Object> model;

            String roundChoice = req.queryParams("roundchoice");

            model = lottoService.searchRound(roundChoice);

            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
