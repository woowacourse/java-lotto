package lotto;

import lotto.controller.IndexController;
import lotto.controller.LottoPurchaseController;
import lotto.controller.ResultController;
import lotto.controller.WinningLottoInputController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(8080);

        staticFiles.location("/static");

        get("/input", LottoPurchaseController.serveInputPage);
        get("/index", IndexController.serveIndexPage);
        get("/winninglotto", WinningLottoInputController.serveWinningLottoInputPage);
        get("/result", ResultController.serveResultPage);
        get("/result/:round", ResultController.responseResultRound);
        get("/showLotto", LottoPurchaseController.showLotto);
        get("/requestRound", ResultController.responseRound);

        post("/budget", LottoPurchaseController.inputBudget);
        post("/manualLotto", LottoPurchaseController.inputManualLotto);
        post("/autoLotto", LottoPurchaseController.inputAutoLotto);
        post("/winningLotto", WinningLottoInputController.inputWinningLotto);
        post("/registerLotto", LottoPurchaseController.registerLotto);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}