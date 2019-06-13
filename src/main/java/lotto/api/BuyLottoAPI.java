package lotto.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lotto.utils.WebParser;
import lotto.data.WebUILottoData;
import lotto.domain.PurchaseAmount;
import lotto.domain.game.Count;
import lotto.domain.game.LottoResult;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;
import spark.Request;

public class BuyLottoAPI {
    public static String index() {
        return TemplateEngine.render(EmptyModel.get(), "index.html");
    }

    public static String buy() {
        return TemplateEngine.render(EmptyModel.get(), "buy.html");
    }

    public static String purchase(Request req, WebUILottoData webUILottoData) {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(Integer.parseInt(req.queryParams("amount")));
        webUILottoData.setPurchaseAmount(purchaseAmount);
        Map<String, Object> model = new HashMap<>();
        model.put("count", String.valueOf(purchaseAmount.ofCount()));
        return TemplateEngine.render(model, "purchase.html");
    }


    public static String manual(Request req, WebUILottoData webUILottoData) {
        Count totalCount = new Count(webUILottoData.getPurchaseAmount());
        webUILottoData.setTotalCount(totalCount);
        ManualCount manualCount = ManualCount.is(Integer.parseInt(req.queryParams("manual")), totalCount);
        webUILottoData.setManualCount(manualCount);
        return TemplateEngine.render(manualModel(webUILottoData, manualCount), "manual.html");
    }

    private static Map<String, Object> manualModel(WebUILottoData webUILottoData, ManualCount manualCount) {
        Map<String, Object> model = new HashMap<>();
        model.put("count", String.valueOf(webUILottoData.getPurchaseAmount().ofCount()));
        model.put("manual", manualCount.toString());
        return model;
    }

    public static String numbers(Request req, WebUILottoData webUILottoData) {
        TotalLottoGames totalLottoGames = new TotalLottoGames(webUILottoData.getManualCount().autoCount(webUILottoData.getTotalCount()));
        for (int i = 0; i < Integer.parseInt(webUILottoData.getManualCount().toString()); i++) {
            List<Number> lotto = InputParser.parseLotto(req.queryParams("manual" + i));
            totalLottoGames.addManual(lotto);
        }
        webUILottoData.setTotalLottoGames(totalLottoGames);
        List<Lotto> list = WebParser.makeLottos(webUILottoData);
        return TemplateEngine.render(numbersModel(webUILottoData, list), "numbers.html");
    }

    private static Map<String, Object> numbersModel(WebUILottoData webUILottoData, List<Lotto> list) {
        Map<String, Object> model = new HashMap<>();
        model.put("auto_count", webUILottoData.getTotalLottoGames().autoSize());
        model.put("manual_count", webUILottoData.getTotalLottoGames().manualSize());
        model.put("lottos", list);
        return model;
    }

    public static String winning(Request req, WebUILottoData webUILottoData) {
        Lotto winningNumbers = new Lotto(InputParser.parseLotto(req.queryParams("winninglotto")));
        webUILottoData.setWinningNumbers(winningNumbers);
        List<Lotto> list = WebParser.makeLottos(webUILottoData);
        return TemplateEngine.render(winningModel(webUILottoData, list), "winning.html");
    }

    private static Map<String, Object> winningModel(WebUILottoData webUILottoData, List<Lotto> list) {
        Map<String, Object> model = new HashMap<>();
        model.put("lottos", list);
        model.put("auto_count", webUILottoData.getTotalLottoGames().autoSize());
        model.put("manual_count", webUILottoData.getTotalLottoGames().manualSize());
        model.put("winning_numbers", webUILottoData.getWinningNumbers().toString());
        return model;
    }

    public static String result(Request req, WebUILottoData webUILottoData) {
        Number bonus = Number.of(InputParser.parseNumber(req.queryParams("bonusnumber")));
        webUILottoData.setBonusNumber(bonus);
        List<String> result = WebParser.result(webUILottoData);
        Long rate = Math.round(LottoResult.rateOfReturn(webUILottoData.getPurchaseAmount()));
        return TemplateEngine.render(resultModel(result, rate), "result.html");
    }

    private static Map<String, Object> resultModel(List<String> result, Long rate) {
        Map<String, Object> model = new HashMap<>();
        model.put("result", result);
        model.put("rate", rate);
        return model;
    }
}
