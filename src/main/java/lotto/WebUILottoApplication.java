package lotto;

import lotto.controller.ErrorController;
import lotto.controller.LottoController;
import lotto.controller.ResultController;
import lotto.controller.SearchController;
import lotto.domain.*;
import lotto.domain.dao.LottoDao;
import lotto.domain.dao.ResultDao;
import lotto.domain.dao.RoundDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.dto.ResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

        LottoController lottoController = new LottoController();
        ResultController resultController = new ResultController();
        ErrorController errorController = new ErrorController();
        SearchController searchController = new SearchController();

        get("/", lottoController::main);

        post("/manualLotto", lottoController::printManual);

        post("/result", resultController::print);

        get("/error", errorController::printMessage);

        post("/searchRound", searchController::searchRound);

        exception(Exception.class, errorController::catchException);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
