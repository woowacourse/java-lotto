package lotto;

import lotto.controller.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get(IndexController.PATH_INDEX, IndexController.serveIndexPage);

        post(LottoMoneyController.PATH_LOTTO_MONEY, LottoMoneyController.fetchLottoMoney);

        post(LottoTicketController.PATH_NUM_OF_MANUAL_LOTTO, LottoTicketController.fetchNumOfManualLotto);
        post(LottoTicketController.PATH_MANUAL_LOTTO, LottoTicketController.fetchManualLotto);
        post(LottoTicketController.PATH_AUTOMATIC_LOTTO, LottoTicketController.fetchAutomaticLotto);

        post(WinningLottoController.PATH_WINNING_LOTTO, WinningLottoController.fetchWinningLotto);
        post(LottoResultController.PATH_LOTTO_RESULT, LottoResultController.fetchLottoResult);
        post(LottoResultController.PATH_LAST_LOTTO_RESULT, LottoResultController.fetchLastLottoResult);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
