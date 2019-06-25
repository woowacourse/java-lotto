package lotto;

import lotto.domain.lotto.InvalidLottoException;
import lotto.domain.purchase.InvalidPurchaseAmountException;
import lotto.domain.purchase.InvalidPurchaseCountException;
import lotto.domain.purchase.PurchaseAmount;
import lotto.domain.purchase.PurchaseCount;
import lotto.domain.result.InvalidWinningException;
import lotto.domain.result.LottoResult;
import lotto.service.*;
import lotto.utils.DBUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static Connection connection = DBUtils.getConnection();
    private static LottoRoundService lottoRoundService = new LottoRoundService(connection);
    private static LottoPurchaseService lottoPurchaseService = new LottoPurchaseService(connection);
    private static LottoWinningService lottoWinningService = new LottoWinningService(connection);
    private static LottoResultService lottoResultService = new LottoResultService(connection);
    private static LottoYieldService lottoYieldService = new LottoYieldService(connection);

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/lottoRound", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoRound", lottoRoundService.inquireTotalRound());
            return render(model, "lottoRound.html");
        });

        get("/lottoRound/:round", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = Integer.parseInt(req.params(":round"));

            model.put("lottoGroup", lottoPurchaseService.inquireLottos(round));
            model.put("winning", lottoWinningService.inquireWinningLotto(round));
            model.put("lottoResult", lottoResultService.inquireLottoResult(round).entrySet());
            model.put("yield", lottoYieldService.inquireLottoYield(round));

            return render(model, "lottoRoundResult.html");
        });

        post("/purchase", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            Integer purchaseAmount = Integer.parseInt(req.queryParams("purchaseAmount"));
            Integer manualCount = Integer.parseInt(req.queryParams("manualCount"));

            PurchaseCount purchaseCount = PurchaseCount.of(PurchaseAmount.of(purchaseAmount), manualCount);
            lottoRoundService.playLottoGame();

            req.session().attribute("purchaseCount", purchaseCount);
            model.put("purchaseCount", purchaseCount);

            return render(model, "purchase.html");
        });

        post("/purchaseLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            PurchaseCount purchaseCount = req.session().attribute("purchaseCount");
            lottoPurchaseService.saveLottos(purchaseCount, req.queryParamsValues("manualLotto"));
            model.put("lottoGroup", lottoPurchaseService.inquireLottos());
            model.put("purchaseCount", purchaseCount);

            return render(model, "winning.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            lottoWinningService.saveWinningLotto(req.queryParams("winningLotto"), req.queryParams("bonusBall"));

            LottoResult lottoResult = lottoRoundService.createLottoResult(lottoWinningService.inquireWinningLotto(), lottoPurchaseService.inquireLottos());

            lottoResultService.saveLottoResult(lottoResult);
            lottoYieldService.saveLottoYield(lottoResult);

            model.put("lottoResult", lottoResultService.inquireLottoResult().entrySet());
            model.put("yield", lottoYieldService.inquireLottoYield());

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