package lotto.controller;


import lotto.domain.CountOfManual;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.service.LottoHelper;
import lotto.service.LottoService;
import lotto.service.RoundService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.*;

import static lotto.WebUILottoApplication.render;

public class LottoController {
    private static final String DELIMITER = "\r\n";

    private final RoundService roundService;
    private final LottoService lottoService;

    public LottoController(final RoundService roundService, final LottoService lottoService) {
        this.roundService = roundService;
        this.lottoService = lottoService;
    }

    public Object addLottos(Request req, Response res) throws SQLException {
        int round = roundService.increaseOne();
        Money money = Money.from(req.queryParams("money"));
        List<String> manualLottos = convertToList(req.queryParams("manualLottos"));
        List<Lotto> userLottos = LottoHelper.generateLottos(manualLottos, money);
        lottoService.save(userLottos, round);

        CountOfManual countOfManual = CountOfManual.of(manualLottos.size(), money.getCountOfPurchase());
        int countOfAuto = money.getCountOfAuto(countOfManual.value());
        res.redirect("/lottos?countOfManual=" + countOfManual.value() + "&countOfAuto=" + countOfAuto);
        return null;
    }

    private List<String> convertToList(final String manualLottos) {
        String[] results = manualLottos.split(DELIMITER);
        if (results[0].equals("")) {
            return new ArrayList<>();
        }
        return Arrays.asList(results);
    }

    public Object getLottos(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = roundService.getLatestRound();
        List<Lotto> lottos = lottoService.getByRound(round);

        model.put("lottos", lottos);
        model.put("countOfAuto", req.queryParams("countOfAuto"));
        model.put("countOfManual", req.queryParams("countOfManual"));
        model.put("round", round);
        return render(model, "showLottos.html");
    }
}
