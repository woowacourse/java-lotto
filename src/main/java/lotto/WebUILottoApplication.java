package lotto;

import lotto.domain.dao.WinningLottoDAO;
import lotto.domain.service.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "home.html");
        });

        get("/round", (req, res) -> {
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO();
            return winningLottoDAO.getLatestRound();
        });

        // 구입 금액
        get("/money", (req, res) -> {
            MoneyService moneyService = new MoneyService();
            moneyService.addMoney(req.queryParams("money"), req.queryParams("round"));
            return res.status();
        });

        get("/manualLotto", (req, res) -> {
            ManualLottoService manualLottoService = new ManualLottoService();
            manualLottoService.addLotto(req.queryParams("manualLotto"), req.queryParams("round"));
            return res.status();
        });

        get("/autoLotto", (req, res) -> {
            LottoService lottoService = new LottoService();
            lottoService.addLotto(req.queryParams("round"), req.queryParams("purchaseLottoCount"), req.queryParams("manualLottoCount"));
            return res.status();
        });

        get("/winningLotto", (req, res) -> {
            WinningLottoService winningLottoService = new WinningLottoService();
            winningLottoService.addWinningLotto(req.queryParams("round"), req.queryParams("bonusNumber"), req.queryParams("winningLotto"));
            return res.status();
        });

        get("/result", (req, res) -> {
           ResultService resultService = new ResultService();
           resultService.getResult(req.queryParams("round"));
           return 0;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
