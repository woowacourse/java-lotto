package lotto;

import lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.externalLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/manualLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int price = Integer.parseInt(req.queryParams("price"));
            int manualCount = Integer.parseInt(req.queryParams("manualCount"));
            LottoMoney lottoMoney = new LottoMoney(price);

            List<String> manualLottos = new ArrayList<>();
            for (int i = 0; i < manualCount; i++) {
                manualLottos.add(req.queryParams("lotto" + i));
            }

            Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());
            req.session().attribute("totalLottos", totalLottos);
            model.put("autoCount", lottoMoney.getCountOfTicket() - manualCount);
            model.put("manualCount", manualCount);
            model.put("lottos", totalLottos);
            return render(model, "manualLotto.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String input = req.queryParams("winningLotto");
            int bonusNum = Integer.parseInt(req.queryParams("bonusBall"));
            WinningLotto winningLotto = new WinningLotto(input, bonusNum);
            LottoResult lottoResult = new LottoResult(req.session().attribute("totalLottos"), winningLotto);
            Map<Rank, Integer> winners = lottoResult.getWinners();

            for (Rank value : Rank.values()) {
                model.put(value.name(), winners.get(value));
            }
            model.put("yield", lottoResult.getYield() * 100);
            return render(model, "result.html");
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });

        exception(Exception.class, (e, req, res) -> {
            try {
                res.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                res.redirect("/error?message=" + "URL Encoding Error");
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
