package lotto;

import lotto.domain.BoughtLottos;
import lotto.domain.Money;
import lotto.domain.Result;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static lotto.service.LottoService.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        String projectDir = "C:\\Users\\PKCH\\Desktop\\techcourse\\level1\\java-lotto-1\\";
        externalStaticFileLocation(projectDir + "src\\main\\resources\\templates\\");
        port(8080);
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        post("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            Money money = generateMoney(req.queryParams("buy-price"));
            BoughtLottos boughtLottos = generateBoughtLottos(money, req.queryParams("lotto-numbers"));
            Result result = generateResult(boughtLottos, req.queryParams("winning-numbers"), req.queryParams("bonus"));

            model.put("money", money);
            model.put("boughtLottos", boughtLottos);
            model.put("result", result);
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
