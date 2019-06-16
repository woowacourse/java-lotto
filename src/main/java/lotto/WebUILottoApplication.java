package lotto;

import lotto.service.LottoService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static LottoService lottoService = new LottoService();

    public static void main(String[] args) {
        get("/", (req, res) -> render(lottoService.offerLottoRounds(), "main.html"));

        post("/lotto", (req, res) -> {
            int money = Integer.parseInt(req.queryParams("money"));
            int manualRound = Integer.parseInt(req.queryParams("manualRound"));
            String manualNumbers = req.queryParams("manualNumbers");
            int lottoRound = Integer.parseInt(req.queryParams("lottoRound"));

            return render(lottoService.offerLottoInfo(money, manualRound, manualNumbers, lottoRound), "lotto.html");
        });

        post("/winLotto", (req, res) -> {
            int round = Integer.parseInt(req.queryParams("round"));
            String userLottoString = req.queryParams("userLotto");
            return render(lottoService.offerUserLottoInfo(round, userLottoString), "winNumber.html");
        });

        get("/result", (req, res) -> {
            String winNumbers = req.queryParams("winNumbers");
            String bonusNumber = req.queryParams("bonusNumber");
            int round = Integer.parseInt(req.queryParams("round"));
            String userLottoString = req.queryParams("userLotto");
            return render(lottoService.offerResults(winNumbers, bonusNumber, userLottoString, round), "result.html");
        });
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
