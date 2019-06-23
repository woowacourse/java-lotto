package lotto;

import lotto.presentation.HomeController;
import lotto.presentation.LottoController;
import lotto.presentation.ResultController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        HomeController homeController = new HomeController();
        get(HomeController.HOME_URL, (req, res) -> homeController.get());
        post(HomeController.HOME_URL, homeController::post);

        LottoController lottoController = new LottoController();
        get(LottoController.LOTTO_URL, (req, res) -> lottoController.get(req));
        post(LottoController.LOTTO_URL, lottoController::post);

        ResultController resultController = new ResultController();
        get(ResultController.RESULT_URL, (req, res) -> resultController.get(req));

        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
            res.body("<script> alert('" + e.getMessage() + "'); history.back()</script>");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
