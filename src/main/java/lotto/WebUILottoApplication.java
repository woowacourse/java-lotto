package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", (req, res) -> render(null, "home.html"));

        get("/money", (req, res) -> render(WebUILottoController.getFutureRound(), "paymoney.html"));

        post("/money", (req, res) -> WebUILottoController.addGameAndMoney(req, res));

        get("/manual-quantity", (req, res) -> render(WebUILottoController.getBuyableQuantity(), "inputmanualquantity.html"));

        post("/manual-quantity", (req, res) -> WebUILottoController.addAutoLottos(req, res));

        get("/manual-numbers", (req, res) -> render(WebUILottoController.getManualQuantity(req), "inputmanualnumbers.html"));

        post("/manual-numbers", (req, res) -> WebUILottoController.addManualLottos(req, res));

        get("/winning", (req, res) -> render(WebUILottoController.getLottosInfo(), "inputwinningnumbers.html"));

        post("/winning", (req, res) -> WebUILottoController.addLottoResult(req, res));

        get("/result", (req, res) -> render(WebUILottoController.getResultInfo(), "reportresult.html"));

        get("/lookup", (req, res) -> render(WebUILottoController.getLastRound(), "lookupresult.html"));

        post("/lookup", (req, res) -> WebUILottoController.deliverRoundInfo(req, res));

        get("/report", (req, res) -> render(WebUILottoController.getAllGameInfo(req), "reportlookup.html"));

        exception(Exception.class, (exception, request, response) -> {
            response.body(String.format("<script>alert('%s'); history.back();</script>", exception.getMessage(), request.pathInfo()));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
