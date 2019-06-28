package lottoGame.money;

import domain.Lotto;
import lottoGame.util.Path;
import lottoGame.util.ViewName;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;
import util.MoneyParser;

import java.util.HashMap;

public class MoneyController {
    public static Route inputMoney = (Request req, Response res) -> new HandlebarsTemplateEngine()
            .render(new ModelAndView(new HashMap<>(), ViewName.Handlebars.MONEY));

    public static Route handleMoneyPost = (Request req, Response res) -> {
        Money money = MoneyParser.parse(req.queryParams("money"));
        int numTotalLottos = money.toInt() / Lotto.PRICE.toInt();

        res.redirect(Path.Web.NUM_NON_RANDOM + "?numTotalLottos=" + numTotalLottos);

        return null;
    };
}
