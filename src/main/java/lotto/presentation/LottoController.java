package lotto.presentation;

import lotto.service.LottoService;
import lotto.service.RoundService;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.presentation.Utils.render;

public class LottoController {
    public static final String LOTTO_URL = "/lotto";

    public String get(Request req) {
        Map<String, Object> model = new HashMap<>();
        model.put("numOfCustomLottos", req.session().attribute("numOfCustomLottos"));
        return render(model, "lotto.html");
    }

    public String post(Request req, Response res) throws SQLException {
        int lottoBuyingMoney = req.session().attribute("lottoBuyingMoney");
        int numOfCustomLottos = req.session().attribute("numOfCustomLottos");

        String winningLotto = req.queryParams("winningLotto");
        List<String> customLottos = new ArrayList<>();
        for (int i = 0; i < numOfCustomLottos; i++) {
            customLottos.add(req.queryParams("lotto" + i));
        }

        LottoService.saveResult(lottoBuyingMoney, numOfCustomLottos, winningLotto, customLottos);
        res.redirect("/result/" + RoundService.getThisLottoRoundId());
        return null;
    }
}
