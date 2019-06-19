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
    private static final String MANUAL_LOTTO_HTML = "manualLotto.html";
    private static final String HOME_HTML = "home.html";

    private final RoundDao roundDao;
    private final LottoDao lottoDao;

    public LottoController(final RoundDao roundDao, final LottoDao lottoDao) {
        this.roundDao = roundDao;
        this.lottoDao = lottoDao;
    }

    public Object main(Request req, Response res) throws SQLException {
        List<Integer> rounds = IntStream.range(1, roundDao.getMaxRound() + 1)
                .boxed()
                .collect(Collectors.toList());

        Map<String, Object> model = new HashMap<>();
        model.put("rounds", rounds);
        return render(model, HOME_HTML);
    }

    public Object printManual(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();

        int manualCount = getManualCount(req, model);
        LottoMoney lottoMoney = getLottoMoney(req, model, manualCount);
        List<String> manualLottos = getManualLottos(req, manualCount);
        Lottos totalLottos = getTotalLottos(lottoMoney, manualLottos, model);

        req.session().attribute("totalLottos", totalLottos);
        return render(model, MANUAL_LOTTO_HTML);
    }

    private int getManualCount(Request req, Map<String, Object> model) {
        int manualCount = Integer.parseInt(req.queryParams("manualCount"));
        model.put("manualCount", manualCount);
        return manualCount;
    }

    private LottoMoney getLottoMoney(Request req, Map<String, Object> model, int manualCount) {
        int price = Integer.parseInt(req.queryParams("price"));
        LottoMoney lottoMoney = new LottoMoney(price);
        model.put("autoCount", lottoMoney.getCountOfTicket() - manualCount);
        return lottoMoney;
    }

    private List<String> getManualLottos(Request req, int manualCount) {
        List<String> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            manualLottos.add(req.queryParams("lotto" + i));
        }
        return manualLottos;
    }

    private Lottos getTotalLottos(LottoMoney lottoMoney, List<String> manualLottos, Map<String, Object> model) throws SQLException {
        roundDao.addNextRound();
        int maxRound = roundDao.getMaxRound();
        Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());
        lottoDao.addTotalLottos(maxRound, totalLottos);
        model.put("lottos", totalLottos);
        return totalLottos;
    }
}
