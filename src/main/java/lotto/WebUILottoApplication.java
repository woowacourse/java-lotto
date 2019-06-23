package lotto;

import lotto.domain.service.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUILottoApplication {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            req.session(true);
            return render(model, "main.html");
        });

        get("/startGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            WinningLottoService  winningLottoService = new WinningLottoService();

            req.session().attribute("newRound", winningLottoService.getNewRound());
            model.put("newRound", req.session().attribute("newRound"));

            return render(model, "lotto.html");
        });

        get("/saveGame", (req, res) -> {
            GameService gameService = new GameService();
            gameService.saveGame(req.session().attribute("newRound"), req.queryParams("manualLottos"),
                    req.queryParams("totalPurchaseCount"), req.queryParams("manualCount"),
                    req.queryParams("bonusNumber"),req.queryParams("winningLottoNumber"), req.queryParams("money"));

            return res.status();
        });

        get("/showGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResultService resultService = new ResultService();
            model.put("resultDTO", resultService.getResult(req.session().attribute("newRound")));
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
