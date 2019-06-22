package lotto;

import lotto.service.*;
import org.javatuples.Pair;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/home", (req, res) -> {
            int lottoBuyingMoney = LottoBuyingMoneyService.validateLottoBuyingMoney(req.queryParams("lottoBuyingMoney"));
            Pair<Integer, Integer> numOfLottos = LottoCountService.calculateNumOfLottos(lottoBuyingMoney, req.queryParams("numOfCustomLottos"));
            int numOfCustomLottos = numOfLottos.getValue0();
            int numOfAutoLottos = numOfLottos.getValue1();

            req.session().attribute("lottoBuyingMoney", lottoBuyingMoney);
            req.session().attribute("numOfCustomLottos", numOfCustomLottos);
            req.session().attribute("numOfAutoLottos", numOfAutoLottos);

            res.redirect("/lotto");
            return null;
        });

        get("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("numOfCustomLottos", req.session().attribute("numOfCustomLottos"));
            return render(model, "lotto.html");
        });

        post("/lotto", (req, res) -> {
            int lottoBuyingMoney = req.session().attribute("lottoBuyingMoney");
            int numOfCustomLottos = req.session().attribute("numOfCustomLottos");

            String winningLotto = req.queryParams("winningLotto");
            List<String> customLottos = new ArrayList<>();
            for (int i = 0; i < numOfCustomLottos; i++) {
                customLottos.add(req.queryParams("lotto" + i));
            }

            LottoService.saveResult(lottoBuyingMoney, numOfCustomLottos, winningLotto, customLottos);
            res.redirect("/result/" + RoundService.getThisLottoRoundId());
            return null;
        });

        get("/result/:roundId", (req, res) -> {
            int thisRoundId = Integer.parseInt(req.params("roundId"));
            ResultDTO result = ResultService.getResultByRoundId(thisRoundId);
            Map<String, Object> model = new HashMap<>();
            model.put("result", result);
            return render(model, "result.html");
        });

        exception(Exception.class, (e, req, res) -> {
            e.printStackTrace();
            res.body("<script> alert('" + e.getMessage() + "'); history.back()</script>");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
