package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.dao.LottoDao;
import lotto.domain.dao.ResultDao;
import lotto.domain.dao.WinningLottoDao;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class SearchController {
    private static final int PERCENT = 100;
    private static final String ROUND_HTML = "round.html";

    private final LottoDao lottoDao;
    private final WinningLottoDao winningLottoDao;
    private final ResultDao resultDao;

    public SearchController(final LottoDao lottoDao, final WinningLottoDao winningLottoDao, final ResultDao resultDao) {
        this.lottoDao = lottoDao;
        this.winningLottoDao = winningLottoDao;
        this.resultDao = resultDao;
    }

    public Object searchRound(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        int currentRound = getCurrentRound(req, model);

        getLottos(model, currentRound);
        getWinners(model, currentRound);
        getWinningLotto(model, currentRound);
        getBonusNum(model, currentRound);
        getYield(model, currentRound);
        getWinPrize(model, currentRound);

        return render(model, ROUND_HTML);
    }

    private void getWinPrize(Map<String, Object> model, int currentRound) throws SQLException {
        long winPrize = resultDao.findWinPrizeByRound(currentRound);
        model.put("winPrize", winPrize);
    }

    private void getYield(Map<String, Object> model, int currentRound) throws SQLException {
        double yield = resultDao.findYieldByRound(currentRound);
        model.put("yield", yield * PERCENT);
    }

    private void getBonusNum(Map<String, Object> model, int currentRound) throws SQLException {
        int bonusNum = winningLottoDao.findBonusNumByRound(currentRound);
        model.put("bonusNum", bonusNum);
    }

    private void getWinningLotto(Map<String, Object> model, int currentRound) throws SQLException {
        List<Integer> winningLotto = winningLottoDao.findWinningLottoByRound(currentRound);
        model.put("winningLotto", winningLotto);
    }

    private void getWinners(Map<String, Object> model, int currentRound) throws SQLException {
        Map<Rank, Integer> winners = resultDao.findWinnerCountByRound(currentRound);
        for (Rank rank : Rank.values()) {
            model.put(rank.name(), winners.get(rank));
        }
    }

    private void getLottos(Map<String, Object> model, int currentRound) throws SQLException {
        List<Lotto> lottos = lottoDao.findLottoByRound(currentRound);
        model.put("lottos", lottos);
    }

    private int getCurrentRound(Request req, Map<String, Object> model) {
        int currentRound = Integer.parseInt(req.queryParams("round_selector"));
        model.put("round", currentRound);
        return currentRound;
    }
}
