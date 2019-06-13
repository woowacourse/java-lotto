package lotto;

import lotto.domain.BoughtLottos;
import lotto.domain.WinningNumber;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) throws SQLException {
        String projectDir = "C:\\Users\\PKCH\\Desktop\\techcourse\\level1\\java-lotto-1\\";
        externalStaticFileLocation(projectDir + "src\\main\\resources\\templates\\");
        port(8080);
        staticFiles.location("/static");

        LottoService lottoService = new LottoService();
        int round = lottoService.getCurrentRound() + 1;

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        get("/result/:round", (req, res) -> {
            int urlRound = Integer.parseInt(req.params(":round"));
            Map<String, Object> model = lottoService.findAllDataOfRound(urlRound);

            return render(model, "result.html");
        });

        post("/lotto", (req, res) -> {
            BoughtLottos boughtLottos = lottoService.generateBoughtLottos(round,
                    req.queryParams("buy-price"), req.queryParams("lotto-numbers"));
            WinningNumber winningNumber = lottoService.generateWinningNumber(round,
                    req.queryParams("winning-numbers"), req.queryParams("bonus"));
            lottoService.generateResult(round, boughtLottos, winningNumber);

            res.redirect("/result/" + round);
            return true;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
