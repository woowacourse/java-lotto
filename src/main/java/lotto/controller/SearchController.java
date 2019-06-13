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
    private LottoDao lottoDao = new LottoDao();
    private ResultDao resultDao = new ResultDao();
    private WinningLottoDao winningLottoDao = new WinningLottoDao();

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
        model.put("yield", yield * 100);
        model.put("winPrize", winPrize);

        return render(model, "round.html");
    }
}
