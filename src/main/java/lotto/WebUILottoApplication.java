package lotto;

import lotto.controller.LottoPurchaseController;
import lotto.controller.WinningLottoController;
import lotto.service.LottoResultService;
import lotto.service.LottoTicketsService;
import lottogame.domain.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", WebUILottoApplication::indexShow);
        get("/error", WebUILottoApplication::redirectErrorPage);
        path("/lottogame/money", () -> {
            get("", LottoPurchaseController::showMoneyInputPage);
            post("", LottoPurchaseController::showMoneyInputPage);
        });
        post("/lottogame/numberofmanual", LottoPurchaseController::showNumberOfManualInputPage);
        post("/lottogame/createmanuallotto", LottoPurchaseController::showManualLottoInputPage);
        path("/lottogame/purchaseresult", () -> {
            get("", LottoPurchaseController::showPurchaseResultGet);
            post("", LottoPurchaseController::showPurchaseResultPost);
        });
        get("/winninglotto/create", WinningLottoController::showWinningLottoInputPage);
//        post("/winninglotto/getresult", );

        exception(RuntimeException.class, (e, req, res) -> {
            String message = null;
            try {
                message = URLEncoder.encode(e.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException error) {
                System.out.println(error.getMessage());
            }
            res.redirect("/error?message=" + message);
        });
    }

    private static Object redirectErrorPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", req.queryParams("message"));
        return render(model, "error.html");
    }

    private static Object indexShow(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = LottoResultService.findPresentRound();
        req.session().attribute("round", round);
        return render(model, "index.html");
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
