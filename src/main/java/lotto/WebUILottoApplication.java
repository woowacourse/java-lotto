package lotto;

import lotto.controller.ErrorController;
import lotto.controller.LottoController;
import lotto.controller.ResultController;
import lotto.controller.SearchController;
import lotto.domain.dao.LottoDao;
import lotto.domain.dao.ResultDao;
import lotto.domain.dao.RoundDao;
import lotto.domain.dao.WinningLottoDao;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        port(8080);


        LottoDao lottoDao = new LottoDao();
        ResultDao resultDao = new ResultDao();
        RoundDao roundDao = new RoundDao();
        WinningLottoDao winningLottoDao = new WinningLottoDao();

        LottoController lottoController = new LottoController(roundDao, lottoDao);
        ResultController resultController = new ResultController(roundDao, winningLottoDao, resultDao);
        SearchController searchController = new SearchController(lottoDao, winningLottoDao, resultDao);
        ErrorController errorController = new ErrorController();

        get("/", lottoController::main);

        post("/manualLotto", lottoController::printManual);

        post("/result", resultController::print);

        post("/searchRound", searchController::searchRound);

        get("/error", errorController::printMessage);

        exception(Exception.class, errorController::catchException);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
