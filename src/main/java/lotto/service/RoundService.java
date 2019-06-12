package lotto.service;

import lotto.dao.LottoDao;
import lotto.dao.WinPrizeDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.WinPrize;
import lotto.view.ResultFormat;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class RoundService {
    public static Object round(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(req.queryParams("round"));
        WinPrize winPrize = new WinPrizeDao().findByRound(round);

        model.put("results", ResultFormat.format(winPrize));
        model.put("rateOfProfit", winPrize.getRateOfProfit());
        model.put("round", round);
        model.put("lottos", new LottoDao().findByRound(round));
        model.put("winningLotto", new WinningLottoDao().findByRound(round));
        return render(model, "round.html");
    }

}
