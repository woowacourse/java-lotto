package lotto;

import lotto.controller.*;
import lotto.domain.LottosResult;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.util.CustomStringUtils;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static int ADD_ONE_BEFORE_ADD_LOTTO_GAME = 1;
    private static int ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER = 1;

    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", (req, res) -> render(null, "home.html"));

        get("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("round", LottoGameController.getLastRound() + ADD_ONE_BEFORE_ADD_LOTTO_GAME);

            return render(model, "paymoney.html");
        });

        post("/money", (req, res) -> {
            LottoGameController.addLottoGame();
            MoneyController.addMoney(req);

            res.redirect("/manual-quantity");
            return "";
        });

        get("/manual-quantity", (req, res) -> {
            Money money = MoneyController.getMoney();

            Map<String, Object> model = new HashMap<>();
            model.put("totalQuantity", money.getBuyableLottoQuantity());

            return render(model, "inputmanualquantity.html");
        });

        post("/manual-quantity", (req, res) -> {
            String inputQuantity = req.queryParams("manual-quantity");
            CustomStringUtils.checkIsBlank(inputQuantity);

            LottoController.addAutoLottos(inputQuantity);

            res.redirect(String.format("/manual-numbers?quantity=%s", inputQuantity));

            return "";
        });

        get("/manual-numbers", (req, res) -> {
            String quantity = req.queryParams("quantity");
            List<Integer> iterator = IntStream.range(1, Integer.parseInt(quantity) + ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER)
                    .boxed().collect(Collectors.toList());

            Map<String, Object> model = new HashMap<>();
            model.put("quantity", quantity);
            model.put("iterator", iterator);

            return render(model, "inputmanualnumbers.html");
        });

        post("/manual-numbers", (req, res) -> {
            String manualQuantity = req.queryParams("quantity");

            LottoController.addManualLottos(req, manualQuantity);

            res.redirect("/winning");
            return "";
        });

        get("/winning", (req, res) -> {
            List<Lotto> lottos = LottoController.getBoughtLottos();
            long manualQuantity = lottos.stream().filter(lotto -> lotto.isAuto() == false).count();
            long autoQuantity = lottos.stream().filter(lotto -> lotto.isAuto() == true).count();

            Map<String, Object> model = new HashMap<>();
            model.put("round", LottoGameController.getLastRound());
            model.put("lottos", lottos);
            model.put("manualQuantity", manualQuantity);
            model.put("autoQuantity", autoQuantity);

            return render(model, "inputwinningnumbers.html");
        });

        post("/winning", (req, res) -> {
            LottoResultController.addLottoResult(req);

            res.redirect("/result");
            return "";
        });

        get("/result", (req, res) -> {
            LottosResult result = LottoResultController.getLottoResult();

            List<Integer> values = new ArrayList<>();
            for (Rank rank : Rank.values()) {
                values.add(result.valueOf(rank));
            }

            Map<String, Object> model = new HashMap<>();
            model.put("round", LottoGameController.getLastRound());
            model.put("values", values);
            model.put("result", result);

            return render(model, "reportresult.html");
        });

        get("/lookup", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("maxRound", LottoGameController.getLastRound());

            return render(model, "lookupresult.html");
        });

        post("/lookup", (req, res) -> {
            res.redirect(String.format("/report?round=%s", req.queryParams("round")));
            return "";
        });

        get("/report", (req, res) -> {
            int round = CustomStringUtils.parseInt(req.queryParams("round"));

            List<Lotto> lottos = LottoController.getBoughtLottos(round);
            WinningLotto winningLotto = WinningLottoController.getWinningLotto(round);
            LottosResult result = new LottosResult(winningLotto, new Lottos(lottos));

            List<Integer> values = new ArrayList<>();
            for (Rank rank : Rank.values()) {
                values.add(result.valueOf(rank));
            }

            Map<String, Object> model = new HashMap<>();
            model.put("round", round);
            model.put("lottos", lottos);
            model.put("winning", winningLotto);
            model.put("values", values);
            model.put("result", result);

            return render(model, "reportlookup.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.body(String.format("<script>alert('%s'); history.back();</script>", exception.getMessage(), request.pathInfo()));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
