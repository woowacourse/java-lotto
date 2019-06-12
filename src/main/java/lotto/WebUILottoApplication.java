package lotto;

import lotto.Controller.LottosController;
import lotto.Controller.RoundController;
import lotto.Controller.WinningLottoController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {

        get("/", RoundController.MAX_ROUND);
        get("/amount", RoundController.SAVE_PURCHASE_AMOUNT);

        post("/lotto", LottosController.CREATE_LOTTOS);

        post("/winning", WinningLottoController.CREATE_WINNING_LOTTO);
        get("/result", WinningLottoController.GET_RESULT);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}