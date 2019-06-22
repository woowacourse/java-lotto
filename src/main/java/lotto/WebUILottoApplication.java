package lotto;

import lotto.dto.RoundInfoDto;
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
            int lottoRound = Integer.parseInt(req.queryParams("lottoRound"));

            return render(lottoService.offerUserLottoInfo(round, userLottoString, lottoRound), "winNumber.html");
        });

        get("/result", (req, res) -> {
            String winNumbers = req.queryParams("winNumbers");
            String bonusNumber = req.queryParams("bonusNumber");
            int round = Integer.parseInt(req.queryParams("round"));
            String userLottoString = req.queryParams("userLotto");
            int lottoRound = Integer.parseInt(req.queryParams("lottoRound"));
            RoundInfoDto roundInfoDto = new RoundInfoDto(winNumbers, bonusNumber, userLottoString, round, lottoRound);
            return render(lottoService.offerResults(roundInfoDto), "result.html");
        });

        get("/hits", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "hits.html");
        });

        post("/lottoHits", (req, res) -> {
            int lottoRound = Integer.parseInt(req.queryParams("hitsRound"));
            return render(lottoService.offerHitsStatus(lottoRound), "hitsResult.html");
        });
    }

    private static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
