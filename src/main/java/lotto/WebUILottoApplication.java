package lotto;

import lotto.domain.controller.LottoPurchaseController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(8080);

        staticFiles.location("/static");

        get("/input", LottoPurchaseController.serveInputPage);

        post("/budget", LottoPurchaseController.inputBudget);
        post("/manualLotto", LottoPurchaseController.inputManualLotto);
        post("/autoLotto", LottoPurchaseController.inputAutoLotto);
        post("/showLotto", LottoPurchaseController.showLotto);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}