package lotto;

import lotto.controller.ErrorController;
import lotto.controller.LottoController;
import lotto.controller.ResultController;
import lotto.controller.SearchController;
import lotto.domain.dao.LottoDao;
import lotto.domain.dao.ResultDao;
import lotto.domain.dao.RoundDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.util.DBUtil;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import javax.sql.DataSource;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String FIRST_PAGE = "/";
    private static final String EXTERNAL_FILE_LOCATION = "src/main/resources/templates";
    private static final String BUY_MANUAL_LOTTO = "/manualLotto";
    private static final String RESULT = "/result";
    private static final String SEARCH_ROUND = "/searchRound";
    private static final String ERROR_PAGE = "/error";

    public static void main(String[] args) {
        externalStaticFileLocation(EXTERNAL_FILE_LOCATION);
        port(8080);

        DataSource dataSource = DBUtil.getDataSource();
        LottoDao lottoDao = new LottoDao(dataSource);
        ResultDao resultDao = new ResultDao(dataSource);
        RoundDao roundDao = new RoundDao(dataSource);
        WinningLottoDao winningLottoDao = new WinningLottoDao(dataSource);

        LottoController lottoController = new LottoController(roundDao, lottoDao);
        ResultController resultController = new ResultController(roundDao, winningLottoDao, resultDao);
        SearchController searchController = new SearchController(lottoDao, winningLottoDao, resultDao);
        ErrorController errorController = new ErrorController();

        get(FIRST_PAGE, lottoController::main);

        post(BUY_MANUAL_LOTTO, lottoController::printManual);

        post(RESULT, resultController::print);

        post(SEARCH_ROUND, searchController::searchRound);

        get(ERROR_PAGE, errorController::printMessage);

        exception(Exception.class, errorController::catchException);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
