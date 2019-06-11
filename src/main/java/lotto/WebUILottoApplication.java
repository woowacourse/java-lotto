package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.path;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        path("/purchase", () -> {
            get("", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "purchasing_lotto.html");
            });

            get("/ticket", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "lotto_tickets.html");
            });
        });

        path("/statistics", () -> {
            get("/win", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "purchasing_lotto.html");
            });

            get("/ticket", (req, res) -> {
                Map<String, Object> model = new HashMap<>();
                return render(model, "lotto_tickets.html");
            });
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
