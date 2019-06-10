package lotto;

import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.domain.game.Count;
import lotto.domain.game.LottoResult;
import lotto.domain.game.ManualCount;
import lotto.domain.game.ResultCounter;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static WebUILottoData webUILottoData = new WebUILottoData();

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/buy", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "purchase.html");
        });

        get("/purchase", (req, res) -> {
            PurchaseAmount purchaseAmount = PurchaseAmount.of(Integer.parseInt(req.queryParams("amount")));
            webUILottoData.setPurchaseAmount(purchaseAmount);
            Map<String, Object> model = new HashMap<>();
            model.put("count", String.valueOf(purchaseAmount.ofCount()));
            return render(model, "manual.html");
        });

        get("/manual", (req, res) -> {
            Count totalCount = new Count(webUILottoData.getPurchaseAmount());
            webUILottoData.setTotalCount(totalCount);
            ManualCount manualCount = ManualCount.is(Integer.parseInt(req.queryParams("manual")), totalCount);
            webUILottoData.setManualCount(manualCount);
            Map<String, Object> model = new HashMap<>();
            model.put("count", String.valueOf(webUILottoData.getPurchaseAmount().ofCount()));
            model.put("manual", manualCount.toString());
            return render(model, "numbers.html");
        });

        post("/numbers", (req, res) -> {
            TotalLottoGames totalLottoGames = new TotalLottoGames(webUILottoData.getManualCount().autoCount(webUILottoData.getTotalCount()));
            for (int i = 0; i < Integer.parseInt(webUILottoData.getManualCount().toString()); i++) {
                totalLottoGames.addManual(InputParser.parseLotto(req.queryParams("manual" + i)));
            }
            webUILottoData.setTotalLottoGames(totalLottoGames);
            Map<String, Object> model = new HashMap<>();
            model.put("autocount", webUILottoData.getTotalLottoGames().autoSize());
            model.put("manualcount", webUILottoData.getTotalLottoGames().manualSize());
            List<Lotto> list = WebParser.makeLottos(webUILottoData);
            model.put("lottos", list);
            return render(model, "all.html");
        });

        get("/winning", (req, res) -> {
            Lotto winningNumber = new Lotto(InputParser.parseLotto(req.queryParams("winninglotto")));
            webUILottoData.setWinningNumbers(winningNumber);
            Map<String, Object> model = new HashMap<>();
            List<Lotto> list = WebParser.makeLottos(webUILottoData);
            model.put("lottos", list);
            model.put("winningnumber", webUILottoData.getWinningNumbers().toString());
            return render(model, "winningbonus.html");
        });

        get("/result", (req, res) -> {
            WinningLotto winningLotto = winningLotto(req);
            List<String> result = result(winningLotto);
            Long rate = Math.round(LottoResult.rateOfReturn(webUILottoData.getPurchaseAmount()));
            Map<String, Object> model = new HashMap<>();
            model.put("result", result);
            model.put("rate", rate);
            return render(model, "result.html");
        });
    }

    private static WinningLotto winningLotto(Request req) {
        Number bonus = Number.of(InputParser.parseNumber(req.queryParams("bonus")));
        webUILottoData.setBonusNumber(bonus);
        WinningLotto winningLotto = new WinningLotto(webUILottoData.getWinningNumbers(), bonus);
        webUILottoData.setWinningLotto(winningLotto);
        return winningLotto;
    }

    private static List<String> result(WinningLotto winningLotto) {
        Map<Rank, ResultCounter> lottoResult = LottoResult.create(webUILottoData.getTotalLottoGames(), winningLotto);
        return WebParser.makeLottoResult(lottoResult);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
