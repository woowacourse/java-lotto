package lotto;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        List<Lottos> lottoRound = new ArrayList<>();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/manualLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int price = Integer.parseInt(req.queryParams("price"));
            int manualCount = Integer.parseInt(req.queryParams("manualCount"));

            List<String> manualLottos = new ArrayList<>();
            for (int i = 0; i < manualCount; i++) {
                manualLottos.add(req.queryParams("lotto" + i));
            }
            int autoCount = price / 1000 - manualCount;
            Lottos totalLottos = new Lottos(manualLottos, autoCount + manualCount);
            lottoRound.add(totalLottos);
            model.put("autoCount", autoCount);
            model.put("manualCount", manualCount);
            model.put("lottos", totalLottos);
            return render(model, "manualLotto.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String input = req.queryParams("winningLotto");
            int bonusNum = Integer.parseInt(req.queryParams("bonusBall"));
            WinningLotto winningLotto = new WinningLotto(input, bonusNum);
            LottoResult lottoResult = new LottoResult(lottoRound.get(0), winningLotto);
            Map<Rank, Integer> winners = lottoResult.getWinners();

            model.put("FIRST", winners.get(Rank.FIRST));
            model.put("SECOND", winners.get(Rank.SECOND));
            model.put("THIRD", winners.get(Rank.THIRD));
            model.put("FOURTH", winners.get(Rank.FOURTH));
            model.put("FIFTH", winners.get(Rank.FIFTH));
            model.put("yield", lottoResult.getYield());
            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
