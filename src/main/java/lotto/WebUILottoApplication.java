package lotto;

import lotto.controller.web.LottoHomeController;
import lotto.controller.web.LottoStartController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        staticFileLocation("/templates");

        get("/home", LottoHomeController::home);
        get("/rule", LottoHomeController::rule);
        get("/contact", LottoHomeController::contact);

        get("/start", LottoStartController::start);
        get("/buy", LottoStartController::buyingLotto);
        get("/amount", LottoStartController::amount);
        post("/numbers", LottoStartController::getLottoTicket);

        post("/result", LottoStartController::getWinningLotto);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
