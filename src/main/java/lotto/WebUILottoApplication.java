package lotto;

import lotto.domain.PurchaseAmount;
import lotto.domain.game.Count;
import lotto.domain.game.LottoResult;
import lotto.domain.game.ManualCount;
import lotto.domain.game.TotalLottoGames;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;
import lotto.utils.InputParser;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static final String RATE_UNIT = "%";
    private static final String AMOUNT_UNIT = "원";

    private static final WebUILottoData webUILottoData = new WebUILottoData();
    private static final GameDTO gameDTO = new GameDTO();

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/buy", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "purchase.html");
        });

        get("/lookup", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            GameDAO gameDAO = new GameDAO();
            model.put("totalCount", gameDAO.getCount());
            return render(model, "lookup.html");
        });

        get("/look", (req, res) -> {
            int games_id = Integer.parseInt(req.queryParams("radio"));
            GameDAO gameDAO = new GameDAO();
            List<String> lottos = gameDAO.getLottosOfGame(games_id);
            GameDTO gameDTO1 = gameDAO.getGameInformation(games_id);
            Map<String, Object> model = new HashMap<>();
            model.put("games_id", games_id);
            model.put("lottos", lottos);
            model.put("winning_number", gameDTO1.getWinningNumbers());
            model.put("bonus", gameDTO1.getBonusNumber());
            model.put("result", gameDTO1.getResult());
            model.put("return_rate", gameDTO1.getReturnRate());
            model.put("return_amount", gameDTO1.getReturnAmount());
            return render(model, "game_results.html");
        });

        get("/purchase", (req, res) -> {
            PurchaseAmount purchaseAmount = PurchaseAmount.of(Integer.parseInt(req.queryParams("amount")));
            webUILottoData.setPurchaseAmount(purchaseAmount);
            Map<String, Object> model = new HashMap<>();
            model.put("count", String.valueOf(purchaseAmount.ofCount()));
            return render(model, "manual.html");
        });

        get("/manual", (req, res) -> {
            Count totalCount = new Count(webUILottoData.getPurchaseAmount());
            webUILottoData.setTotalCount(totalCount);
            ManualCount manualCount = ManualCount.is(Integer.parseInt(req.queryParams("manual")), totalCount);
            webUILottoData.setManualCount(manualCount);
            Map<String, Object> model = new HashMap<>();
            model.put("count", String.valueOf(webUILottoData.getPurchaseAmount().ofCount()));
            model.put("manual", manualCount.toString());
            return render(model, "numbers.html");
        });

        post("/numbers", (req, res) -> {
            TotalLottoGames totalLottoGames = new TotalLottoGames(webUILottoData.getManualCount().autoCount(webUILottoData.getTotalCount()));
            for (int i = 0; i < Integer.parseInt(webUILottoData.getManualCount().toString()); i++) {
                List<Number> lotto = InputParser.parseLotto(req.queryParams("manual" + i));
                totalLottoGames.addManual(lotto);
            }
            webUILottoData.setTotalLottoGames(totalLottoGames);
            Map<String, Object> model = new HashMap<>();
            model.put("auto_count", webUILottoData.getTotalLottoGames().autoSize());
            model.put("manual_count", webUILottoData.getTotalLottoGames().manualSize());
            List<Lotto> list = WebParser.makeLottos(webUILottoData);
            model.put("lottos", list);
            return render(model, "all.html");
        });

        get("/winning", (req, res) -> {
            Lotto winningNumbers = new Lotto(InputParser.parseLotto(req.queryParams("winninglotto")));
            webUILottoData.setWinningNumbers(winningNumbers);
            Map<String, Object> model = new HashMap<>();
            List<Lotto> list = WebParser.makeLottos(webUILottoData);
            model.put("lottos", list);
            model.put("auto_count", webUILottoData.getTotalLottoGames().autoSize());
            model.put("manual_count", webUILottoData.getTotalLottoGames().manualSize());
            model.put("winning_numbers", webUILottoData.getWinningNumbers().toString());
            return render(model, "winning_bonus.html");
        });

        get("/result", (req, res) -> {
            Number bonus = Number.of(InputParser.parseNumber(req.queryParams("bonusnumber")));
            webUILottoData.setBonusNumber(bonus);
            List<String> result = WebParser.result(webUILottoData);
            Long rate = Math.round(LottoResult.rateOfReturn(webUILottoData.getPurchaseAmount()));
            Map<String, Object> model = new HashMap<>();
            model.put("result", result);
            model.put("rate", rate);
            return render(model, "result.html");
        });

        get("/enroll", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            GameDAO gameDAO = new GameDAO();
            List<String> result = WebParser.get();
            Long rate = Math.round(LottoResult.rateOfReturn(webUILottoData.getPurchaseAmount()));
            gameDTO.setWinningNumbers(webUILottoData.getWinningNumbers());
            gameDTO.setBonusNumber(webUILottoData.getBonusNumber());
            gameDTO.setResult(WebParser.forSQL(result));
            gameDTO.setReturnAmount(LottoResult.resultAmount() + AMOUNT_UNIT);
            gameDTO.setReturnRate(rate + RATE_UNIT);
            gameDAO.addGameInformation(gameDTO);
            gameDAO.addLottoNumbers(webUILottoData.getTotalLottoGames().allGames());
            LottoResult.init();
            return render(model, "enroll.html");
        });

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(404);
            res.body("<h1>에러 발생</h1>" +
                    "<form action=\"/\">\n" +
                    "  <input type=\"submit\" value=\"홈으로\"/>\n" +
                    "</form>");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
