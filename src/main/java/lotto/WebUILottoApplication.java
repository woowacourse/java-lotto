package lotto;

import lotto.controller.IndexController;
import lotto.controller.LottoPurchaseController;
import lotto.controller.ResultConroller;
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
        get("/result", ResultConroller.serveResultPage);

        post("/budget", LottoPurchaseController.inputBudget);
        post("/manualLotto", LottoPurchaseController.inputManualLotto);
        post("/autoLotto", LottoPurchaseController.inputAutoLotto);
        post("/showLotto", LottoPurchaseController.showLotto);
        post("/winningLotto", WinningLottoInputController.inputWinningLotto);
        post("/registerLotto", LottoPurchaseController.registerLotto);
        post("/requestResult", ResultConroller.responseResult);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}