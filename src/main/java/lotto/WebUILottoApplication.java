package lotto;

import lotto.controller.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get(Path.INDEX, IndexController.serveIndexPage);

        post(Path.LOTTO_MONEY, LottoMoneyController.fetchLottoMoney);

        post(Path.NUM_OF_MANUAL_LOTTO, LottoTicketController.fetchNumOfManualLotto);
        post(Path.MANUAL_LOTTO, LottoTicketController.fetchManualLotto);
        post(Path.AUTOMATIC_LOTTO, LottoTicketController.fetchAutomaticLotto);

        post(Path.WINNING_LOTTO, WinningLottoController.fetchWinningLotto);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
