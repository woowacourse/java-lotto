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
        int currentRound = Integer.parseInt(req.queryParams("round_selector"));
        model.put("round", currentRound);

        List<Lotto> lottos = lottoDao.findLottoByRound(currentRound);
        Map<Rank, Integer> winners = resultDao.findWinnerCountByRound(currentRound);
        List<Integer> winningLotto = winningLottoDao.findWinningLottoByRound(currentRound);
        int bonusNum = winningLottoDao.findBonusNumByRound(currentRound);
        double yield = resultDao.findYieldByRound(currentRound);
        long winPrize = resultDao.findWinPrizeByRound(currentRound);

        model.put("lottos", lottos);
        for (Rank rank : Rank.values()) {
            model.put(rank.name(), winners.get(rank));
        }
        model.put("winningLotto", winningLotto);
        model.put("bonusNum", bonusNum);
        model.put("yield", yield * PERCENT);
        model.put("winPrize", winPrize);

        return render(model, ROUND_HTML);
    }
}
