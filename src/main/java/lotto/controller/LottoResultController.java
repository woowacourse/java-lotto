package lotto.controller;

import lotto.dao.LottosDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.db.DatabaseConnection;
import lotto.domain.*;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.service.RoundService;
import lotto.service.WinningLottoService;
import lotto.utils.ResultMessage;
import lotto.utils.ViewUtils;
import spark.Route;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.controller.LottoController.PRICE;

public class LottoResultController {
    private static final String YIELD = "yield";
    private static final String LOTTO_RESULT = "userLottoResult";
    private static final String ROUND = "round";
    private static final String WINNING_LOTTO = "winningLotto";
    private static final String BONUS = "bonusNumber";
    private static final String LOTTOS = "lottos";

    public static Route makeLottoResultPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        RoundService roundService = new RoundService();
        LottoService lottoService = new LottoService();
        WinningLottoService winningLottoService = new WinningLottoService();
        LottoResultService lottoResultService = new LottoResultService();

        Lottos lottos = req.session().attribute(LOTTOS);
        WinningLotto winningLotto = winningLottoService.getWinningLotto(req.queryParams(WINNING_LOTTO),req.queryParams(BONUS));
        LottoResult lottoResult = lottoResultService.getLottoResult(lottos,winningLotto);

        Price price = req.session().attribute(PRICE);
        model.put(YIELD, lottoResult.findYield(price.getPrice()));
        model.put(LOTTO_RESULT, ResultMessage.getResult(lottoResult, getRanks()));

        int round = req.session().attribute(ROUND);

        roundService.addRoundInDB(round,price);
        lottoService.addLottosInDB(round,lottos);
        winningLottoService.addWinningLottoInDB(round,winningLotto);

        return ViewUtils.render(model, "result.html");
    };

    public static Route makeLottoResultByRoundPage = (req, res) -> {
        Connection conn = new DatabaseConnection().getConnection();
        RoundDao roundDao = new RoundDao(conn);
        LottosDao lottosDao = new LottosDao(conn);
        WinningLottoDao winningLottoDao = new WinningLottoDao(conn);

        Map<String, Object> model = new HashMap<>();

        int round = Integer.parseInt(req.queryParams(ROUND));
        Lottos lottos = lottosDao.findLottoByRound(round);
        WinningLotto winningLotto = winningLottoDao.findWinningLottoByRound(round);
        LottoResult lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);

        model.put(YIELD, lottoResult.findYield(roundDao.findPriceByRound(round)));
        model.put(LOTTO_RESULT, ResultMessage.getResult(lottoResult, getRanks()));
        return ViewUtils.render(model, "result.html");
    };

    private static List<Rank> getRanks() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIRST);
        ranks.add(Rank.SECOND);
        ranks.add(Rank.THIRD);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.FIFTH);
        return ranks;
    }
}
