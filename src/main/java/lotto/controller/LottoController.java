package lotto.controller;

import lotto.domain.LottoMoney;
import lotto.domain.Lottos;
import lotto.domain.dao.LottoDao;
import lotto.domain.dao.RoundDao;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.WebUILottoApplication.render;

public class LottoController {
    private RoundDao roundDao = new RoundDao();
    private LottoDao lottoDao = new LottoDao();

    public Object main(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        List<Integer> rounds = IntStream.range(1, roundDao.getMaxRound() + 1)
                .boxed()
                .collect(Collectors.toList());
        model.put("rounds", rounds);
        return render(model, "home.html");
    }

    public Object printManual(Request req, Response res) throws SQLException {

        Map<String, Object> model = new HashMap<>();
        int price = Integer.parseInt(req.queryParams("price"));
        int manualCount = Integer.parseInt(req.queryParams("manualCount"));
        LottoMoney lottoMoney = new LottoMoney(price);

        List<String> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(req.queryParams("lotto" + i));
        }

        Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());

        roundDao.addNextRound();
        int maxRound = roundDao.getMaxRound();

        lottoDao.addTotalLottos(maxRound, totalLottos);
        req.session().attribute("totalLottos", totalLottos);

        model.put("autoCount", lottoMoney.getCountOfTicket() - manualCount);
        model.put("manualCount", manualCount);
        model.put("lottos", totalLottos);
        return render(model, "manualLotto.html");
    }
}
