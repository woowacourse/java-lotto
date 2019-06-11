package lotto;

import lotto.controller.IndexController;
import lotto.controller.LottoMoneyController;
import lotto.controller.Path;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.externalStaticFileLocation;
import static spark.Spark.get;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get(Path.INDEX, IndexController.serveIndexPage);

        get(Path.LOTTO_MONEY, LottoMoneyController.fetchLottoMoney);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
