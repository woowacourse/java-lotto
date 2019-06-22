package lotto;

import lotto.domain.lotto.InvalidLottoException;
import lotto.domain.lotto.Lottos;
import lotto.domain.purchase.InvalidPurchaseAmountException;
import lotto.domain.purchase.InvalidPurchaseCountException;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.InvalidWinningException;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Winning;
import lotto.service.ConnectionService;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        ConnectionService.connection();

        externalStaticFileLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/purchase", "application/json", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            Integer purchaseAmount = Integer.parseInt(req.queryParams("purchaseAmount"));
            Integer manualCount = Integer.parseInt(req.queryParams("manualCount"));

            PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(purchaseAmount), manualCount);

            req.session().attribute("purchaseCount", purchaseCount);
            model.put("purchaseCount", purchaseCount);

            return render(model, "purchase.html");
        });

        post("/purchaseLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            PurchaseCount purchaseCount = req.session().attribute("purchaseCount");
            Lottos lottos = new LottoService().createLottos(purchaseCount, req.queryParamsValues("manualLotto"));

            req.session().attribute("lottoGroup", lottos);
            model.put("lottoGroup", lottos.getLottos());
            model.put("purchaseCount", purchaseCount);

            return render(model, "winning.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            LottoService lottoService = new LottoService();

            Winning winning = lottoService.createWinningLotto(req.queryParams("winningLotto"), req.queryParams("bonusBall"));
            LottoResult lottoResult = lottoService.createLottoResult(winning, req.session().attribute("lottoGroup"));

            model.put("yield", lottoResult.yield());
            model.put("lottoResult", lottoService.createLottoMessage(lottoResult.getMap()));

            return render(model, "result.html");
        });

        exception();
    }

    private static void exception() {
        Spark.exception(NumberFormatException.class, WebUILottoApplication::exceptionMsg);
        Spark.exception(InvalidPurchaseAmountException.class, WebUILottoApplication::exceptionMsg);
        Spark.exception(InvalidPurchaseCountException.class, WebUILottoApplication::exceptionMsg);
        Spark.exception(InvalidLottoException.class, WebUILottoApplication::exceptionMsg);
        Spark.exception(InvalidWinningException.class, WebUILottoApplication::exceptionMsg);
    }

    private static void exceptionMsg(Exception exception, Request request, Response response) {
        response.body(
                "<script>" +
                    "alert('" + exception.getMessage() + "');" +
                    "history.back();" +
                "</script>"
        );
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}