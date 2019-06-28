package lottoGame;

import domain.LottoGroup;
import domain.WinningLotto;
import dto.RankAnalysisDTO;
import lottoGame.analysis.LottoAnalysisController;
import lottoGame.index.IndexController;
import lottoGame.lotto.LottoController;
import lottoGame.money.MoneyController;
import lottoGame.numNonRandom.numNonRandomController;
import lottoGame.util.Path;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    // TODO: DB 에 존재하는 최대 TOKEN + 1 로 설정하기
    public static int TOKEN = 0;
    public static Map<Integer, LottoGroup> lottoGroupInMemoryDAO;
    public static Map<Integer, WinningLotto> winningLottoInMemoryDAO;
    public static Map<Integer, RankAnalysisDTO> rankAnalysisDTOInMemoryDAO;

    public static void main(String[] args) {

        // init DAO
        initDAO();

        get(Path.Web.INDEX, IndexController.serveIndexPage);

        get(Path.Web.MONEY, MoneyController.inputMoney);
        post(Path.Web.MONEY, MoneyController.handleMoneyPost);

        get(Path.Web.NUM_NON_RANDOM, numNonRandomController.inputNumNonRandom);
        post(Path.Web.NUM_NON_RANDOM, numNonRandomController.handleNumNonRandomPost);

        get(Path.Web.NON_RANDOM_LOTTOS, LottoController.inputNonRandomLottos);
        post(Path.Web.NON_RANDOM_LOTTOS, LottoController.handleNonRandomLottosPost);

        get(Path.Web.LOTTO_GROUP, LottoController.fetchLottoGroup);
        post(Path.Web.LOTTO_GROUP, LottoController.handleLottoGroupPost);

        get(Path.Web.WINNING_LOTTO, LottoController.inputWinningLotto);
        post(Path.Web.WINNING_LOTTO, LottoController.handleWinningLottoPost);

        get(Path.Web.LOTTO_ANALYSIS, LottoAnalysisController.serveLottoAnalysis);
        post(Path.Web.LOTTO_ANALYSIS, LottoAnalysisController.handleLottoAnalysisPost);
    }

    private static void initDAO() {
        lottoGroupInMemoryDAO = new HashMap<>();
        winningLottoInMemoryDAO = new HashMap<>();
        rankAnalysisDTOInMemoryDAO = new HashMap<>();
    }
}
