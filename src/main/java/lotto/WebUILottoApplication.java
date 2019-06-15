package lotto;

import lotto.domain.Money;
import lotto.view.WebInputParser;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/number", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Money money = WebInputParser.getMoney(req.queryParams("money"));
            } catch (Exception e) {
                model.put("error", e.getMessage());
                return render(model, "error.html");
            }

            return render(model, "number.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
