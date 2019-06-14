package lotto;

import lotto.domain.Money;
import lotto.domain.UserLotto;
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
            Map<String, Object> model = new HashMap<>();

            int round = new Money(Integer.parseInt(req.queryParams("money"))).getRound();
            int manualRound = Integer.parseInt(req.queryParams("manualRound"));
            int autoRound = lottoService.getAutoRound(round, manualRound);
            String[] numbers = lottoService.splitNumbers(req.queryParams("manualNumbers"));
            UserLotto userLotto = lottoService.createUserLotto(numbers, autoRound);

            model.put("round", round);
            model.put("manualRound", manualRound);
            model.put("autoRound", autoRound);
            model.put("userLotto", userLotto.getUserLotto());

            return render(model, "lotto.html");
        });

        post("/winLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("round", Integer.parseInt(req.queryParams("round")));
            model.put("userLotto", req.queryParams("userLotto"));

            return render(model, "winNumber.html");
        });
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
