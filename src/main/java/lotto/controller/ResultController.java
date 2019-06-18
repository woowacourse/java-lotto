package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.dao.ResultDao;
import lotto.domain.dao.RoundDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.dto.ResultDto;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ResultController {
    private static final String RESULT_HTML = "result.html";

    private final RoundDao roundDao;
    private final WinningLottoDao winningLottoDao;
    private final ResultDao resultDao;

    public ResultController(final RoundDao roundDao, final WinningLottoDao winningLottoDao, final ResultDao resultDao) {
        this.roundDao = roundDao;
        this.winningLottoDao = winningLottoDao;
        this.resultDao = resultDao;
    }

    public Object print(Request req, Response res) throws SQLException {
        String input = req.queryParams("winningLotto");
        int bonusNum = Integer.parseInt(req.queryParams("bonusBall"));
        int maxRound = roundDao.getMaxRound();

        WinningLotto winningLotto = getWinningLotto(input, bonusNum, maxRound);
        Map<Rank, Integer> winners = getLottoWinners(req, maxRound, winningLotto);

        Map<String, Object> model = new HashMap<>();
        for (Rank rank : Rank.values()) {
            model.put(rank.name(), winners.get(rank));
        }
        model.put("yield", resultDao.findYieldByRound(maxRound) * 100);
        return render(model, RESULT_HTML);
    }

    private WinningLotto getWinningLotto(String input, int bonusNum, int maxRound) throws SQLException {
        WinningLotto winningLotto = new WinningLotto(input, bonusNum);
        winningLottoDao.addWinningLotto(maxRound, winningLotto);
        return winningLotto;
    }

    private Map<Rank, Integer> getLottoWinners(Request req, int maxRound, WinningLotto winningLotto) throws SQLException {
        LottoResult lottoResult = new LottoResult(req.session().attribute("totalLottos"), winningLotto);
        Map<Rank, Integer> winners = lottoResult.getWinners();
        ResultDto resultDTO = new ResultDto(winners, lottoResult.getYield(), lottoResult.getTotalWinningMoney());
        resultDao.addResult(maxRound, resultDTO);
        return winners;
    }
}
