package lotto;

import lotto.database.*;
import lotto.domain.*;
import lotto.view.WebInputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.view.WebOutputView.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/home", (req, res) -> {
            LottoBuyingMoney lottoBuyingMoney = WebInputView.inputLottoBuyingMoney(req.queryParams("lottoBuyingMoney"));
            LottoCount lottoCount = WebInputView.inputLottoCount(lottoBuyingMoney, req.queryParams("numOfCustomLottos"));
            req.session().attribute("lottoBuyingMoney", lottoBuyingMoney);
            req.session().attribute("lottoCount", lottoCount);
            res.redirect("/lotto");
            return res;
        });

        get("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("numOfCustomLottos", ((LottoCount) req.session().attribute("lottoCount")).custom());
            return render(model, "lotto.html");
        });

        post("/lotto", (req, res) -> {
            WinningLotto winningLotto = WebInputView.inputWinningLotto(req.queryParams("winningLotto"));
            LottoCount lottoCount = req.session().attribute("lottoCount");
            List<List<Integer>> lottos = new ArrayList<>();
            for (int i = 0; i < lottoCount.custom(); i++) {
                lottos.add(WebInputView.inputLotto(req.queryParams("lotto" + i)));
            }
            req.session().attribute("winningLotto", winningLotto);
            req.session().attribute("lottos", LottoVendingMachine.getLottos(lottoCount, lottos));
            res.redirect("/result");
            return res;
        });

        get("/result", (req, res) -> {
            WinningLotto winningLotto = req.session().attribute("winningLotto");
            Lottos lottos = req.session().attribute("lottos");
            WinningStatistics winningStatistics = new WinningStatistics(lottos.match(winningLotto));

            WebResultDTO result = new WebResultDTO();
            result.setWinningLotto(outputWinningLotto(winningLotto));
            result.setInterestRate(String.valueOf(winningStatistics.getInterestRate(req.session().attribute("lottoBuyingMoney"))));
            result.setLottos(outputLottos(lottos));
            result.setPrize(String.valueOf(winningStatistics.getPrize().getValue()));
            result.setResult(outputResult(winningStatistics));

            // Insert data into database
            Connection con = Connector.getConnection();
            RoundDAO roundDao = new RoundDAO(con);
            roundDao.addRound(winningStatistics.getPrize().getValue(), winningStatistics.getInterestRate(req.session().attribute("lottoBuyingMoney")));
            int thisRoundId = roundDao.getLatestRoundId();
            ResultDAO resultDao = new ResultDAO(con);
            resultDao.addResult(thisRoundId, winningStatistics.getStatistics());
            WinningLottoDAO winningLottoDao = new WinningLottoDAO(con);
            winningLottoDao.addWinningLotto(thisRoundId, winningLotto);
            LottoDAO lottoDao = new LottoDAO(con);
            lottoDao.addLotto(thisRoundId, lottos);

            Map<String, Object> model = new HashMap<>();
            model.put("result", result);
            return render(model, "result.html");
        });

        exception(Exception.class, (e, req, res) -> {
            res.body("<script> alert('" + e.getMessage() + "'); history.back()</script>");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
