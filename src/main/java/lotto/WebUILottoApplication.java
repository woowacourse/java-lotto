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
        get("/result", ResultConroller.serveResultPage);                // 최초 결과 페이지 보여주는 GET
        get("/result/:round", ResultConroller.responseResultRound);     // 라운드마다 DB를 요청하는 GET
        get("/showLotto", LottoPurchaseController.showLotto);
        get("/requestRound", ResultConroller.responseRound);            // 라운드를 물어보는 GET

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