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
    private RoundDao roundDao = new RoundDao();
    private WinningLottoDao winningLottoDao = new WinningLottoDao();
    private ResultDao resultDao = new ResultDao();

    public Object print(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String input = req.queryParams("winningLotto");
        int bonusNum = Integer.parseInt(req.queryParams("bonusBall"));
        WinningLotto winningLotto = new WinningLotto(input, bonusNum);
        LottoResult lottoResult = new LottoResult(req.session().attribute("totalLottos"), winningLotto);
        Map<Rank, Integer> winners = lottoResult.getWinners();

        int maxRound = roundDao.getMaxRound();
        winningLottoDao.addWinningLotto(maxRound, winningLotto);
        ResultDto resultDTO = new ResultDto(winners, lottoResult.getYield(), lottoResult.getTotalWinningMoney());
        resultDao.addResult(maxRound, resultDTO);
        for (Rank rank : Rank.values()) {
            model.put(rank.name(), winners.get(rank));
        }
        model.put("yield", resultDao.findYieldByRound(maxRound) * 100);
        return render(model, "result.html");
    }
}
