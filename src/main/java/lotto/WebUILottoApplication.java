package lotto;

import com.google.gson.Gson;
import lotto.controller.LottoResultController;
import lotto.controller.UserLottoController;
import lotto.controller.WinningLottoController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("templates");
        staticFiles.location("templates");
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/buyLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "buy.html");
        });

        post("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lotto.html");
        });

        post("/userLotto", UserLottoController::userLotto, gson::toJson);

        post("/winningLotto", WinningLottoController::winningLotto, gson::toJson);

        get("/lottoResult", LottoResultController::lottoResult, gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
