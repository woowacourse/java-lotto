package lottoGame.lotto;

import domain.Number;
import domain.*;
import lottoGame.money.Money;
import lottoGame.util.Path;
import lottoGame.util.ViewName;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;
import util.LottoParser;
import util.NonNegativeIntegerParse;
import util.NumberParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lottoGame.WebUILottoApplication.TOKEN;

public class LottoController {
    public static Route inputNonRandomLottos = (req, res) -> {
        Map<String, Object> model = new HashMap<>();

        model.put("numTotalLottos", req.queryParams("numTotalLottos"));

        int numNonRandom = NonNegativeIntegerParse.parse(req.queryParams("numNonRandom"));
        model.put("numNonRandom", numNonRandom);
        // [0, numNonRandom)
        model.put("ids", IntStream.range(0, numNonRandom).boxed()
                .collect(Collectors.toList()));

        return new HandlebarsTemplateEngine().render(new ModelAndView(model, ViewName.Handlebars.NON_RANDOM_LOTTOS));
    };

    public static Route handleNonRandomLottosPost = (req, res) -> {
        int numTotalLottos = Integer.parseInt(req.queryParams("numTotalLottos"));
        int numNonRandom = Integer.parseInt(req.queryParams("numNonRandom"));

        List<Lotto> nonRandomLottos = IntStream.range(0, numNonRandom)
                .mapToObj(i -> req.queryParams("lotto-" + i))
                .map(LottoParser::parse)
                .collect(Collectors.toList());

        LottoGroup lottoGroup = LottoSimulator.purchase(nonRandomLottos, Money.from(numTotalLottos * Lotto.PRICE.toInt()));

        int token = TOKEN++;
        LottoDAO.getInstance().addLottoGroup(lottoGroup, token);

        res.redirect(Path.Web.LOTTO_GROUP + "?token=" + token);

        return null;
    };

    public static Route fetchLottoGroup = (req, res) -> {
        int token = Integer.parseInt(req.queryParams("token"));
        LottoGroup lottoGroup = LottoDAO.getInstance().findLottoGroup(token);

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("lottoGroup", lottoGroup);

        return new HandlebarsTemplateEngine().render(
                new ModelAndView(model, ViewName.Handlebars.LOTTO_GROUP));
    };

    public static Route handleLottoGroupPost = (Request req, Response res) -> {
        int token = Integer.parseInt(req.queryParams("token"));

        res.redirect(Path.Web.WINNING_LOTTO + "?token=" + token);

        return null;
    };

    public static Route inputWinningLotto = (req, res) -> {
        int token = Integer.parseInt(req.queryParams("token"));

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);

        return new HandlebarsTemplateEngine().render(
                new ModelAndView(model, ViewName.Handlebars.WINNING_LOTTO));
    };

    public static Route handleWinningLottoPost = (req, res) -> {
        int token = Integer.parseInt(req.queryParams("token"));
        Lotto lotto = LottoParser.parse(req.queryParams("lotto"));
        Number bonusNumber = NumberParser.parse(req.queryParams("bonusNumber"));

        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNumber);

        WinningLottoDAO.getInstance().add(winningLotto, token);

        res.redirect(Path.Web.LOTTO_ANALYSIS + "?token=" + token);
        return null;
    };
}
