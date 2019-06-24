package lotto;

import lotto.domain.controller.Controller;
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
            Map<String, Object> model = Controller.startGame(req);
            return render(model, "lotto.html");
        });

        get("/saveGame", (req, res) -> {
            Controller.saveGame(req);
            return res.status();
        });

        get("/showGame", (req, res) -> {
            Map<String, Object> model = Controller.showGame(req.session().attribute("newRound"));
            return render(model, "result.html");
        });
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
