package lotto.presentation;

import lotto.service.LottoBuyingMoneyService;
import lotto.service.LottoCountService;
import org.javatuples.Pair;
import spark.Request;
import spark.Response;

import static lotto.presentation.Utils.render;

public class HomeController {
    public static final String HOME_URL = "/home";

    public String get() {
        return render(null, "index.html");
    }

    public String post(Request req, Response res) {
        int lottoBuyingMoney = LottoBuyingMoneyService.validateLottoBuyingMoney(req.queryParams("lottoBuyingMoney"));
        Pair<Integer, Integer> numOfLottos = LottoCountService.calculateNumOfLottos(lottoBuyingMoney, req.queryParams("numOfCustomLottos"));
        int numOfCustomLottos = numOfLottos.getValue0();
        int numOfAutoLottos = numOfLottos.getValue1();

        req.session().attribute("lottoBuyingMoney", lottoBuyingMoney);
        req.session().attribute("numOfCustomLottos", numOfCustomLottos);
        req.session().attribute("numOfAutoLottos", numOfAutoLottos);

        res.redirect("/lotto");
        return null;
    }

}
