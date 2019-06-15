package lotto;

import lotto.config.DBConnector;
import lotto.config.DataSource;
import lotto.controller.*;
import lotto.dao.LottoDao;
import lotto.dao.RoundDao;
import lotto.dao.WinPrizeDao;
import lotto.dao.WinningLottoDao;
import lotto.service.LottoService;
import lotto.service.RoundService;
import lotto.service.WinPrizeService;
import lotto.service.WinningLottoService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        port(8080);

        DBConnector dbConnector = new DBConnector(DataSource.getInstance());

        RoundDao roundDao = new RoundDao(dbConnector);
        LottoDao lottoDao = new LottoDao(dbConnector);
        WinPrizeDao winPrizeDao = new WinPrizeDao(dbConnector);
        WinningLottoDao winningLottoDao = new WinningLottoDao(dbConnector);

        RoundService roundService = new RoundService(roundDao);
        LottoService lottoService = new LottoService(lottoDao);
        WinPrizeService winPrizeService = new WinPrizeService(winPrizeDao);
        WinningLottoService winningLottoService = new WinningLottoService(winningLottoDao);

        LottoController lottoController = new LottoController(roundService, lottoService);
        ResultController resultController = new ResultController(winPrizeService, winningLottoService, lottoService);
        ErrorController errorController = new ErrorController();
        MainController mainController = new MainController(roundDao);
        RoundController roundController = new RoundController(winPrizeService, lottoService, winningLottoService);

        externalStaticFileLocation("src/main/resources/templates");

        get("/", mainController::main);

        get("/round", roundController::round);

        post("/lottos", lottoController::addLottos);

        get("/lottos", lottoController::getLottos);

        post("/result", resultController::doPostResult);

        get("/result", resultController::doGetResult);

        get("/error", errorController::exception);

        exception(Exception.class, (exception, req, res) -> {
            String message = null;
            try {
                message = URLEncoder.encode(exception.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/error?message=" + message);
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }


}
