package lotto;

import lotto.dao.DBConnection;
import lotto.dao.JdbcTemplate;
import lotto.dao.LottoRoundDAO;
import lotto.dto.LottoResultDTO;
import lotto.dto.PurchaseInformationDTO;
import lotto.service.LottoHistoryService;
import lotto.service.LottoPurchaseService;
import lotto.service.LottoResultService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(8080);
        Spark.staticFileLocation("/public");

        get("/", WebUILottoApplication::menu);
        get("/purchase", WebUILottoApplication::purchaseLotto);
        post("/winningLotto", WebUILottoApplication::winningLotto);
        post("/result", WebUILottoApplication::result);
        get("/list", WebUILottoApplication::list);
        get("/history", WebUILottoApplication::history);
    }

    private static String menu(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    private static String purchaseLotto(Request request, Response response) {
        try {
            Map<String, Object> model = new HashMap<>();

            JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
            int thisRound = LottoRoundDAO.getInstance(jdbcTemplate).selectMaxRound() + 1;

            request.session().attribute("round", thisRound);
            model.put("round", thisRound);
            return render(model, "purchaseLotto.html");
        } catch (SQLException e) {
            e.printStackTrace();
            return handleError("DB 에러 발생");
        }
    }

    private static String winningLotto(Request request, Response response) {
        try {
            Map<String, Object> model = new HashMap<>();

            PurchaseInformationDTO purchaseInformationDTO = LottoPurchaseService.getInstance().purchaseLottos(
                    request.session().attribute("round"),
                    request.queryParams("purchaseAmount"),
                    request.queryParamsValues("manualNums")
            );

            model.put("purchaseInformation", purchaseInformationDTO);
            request.session().attribute("lottos", purchaseInformationDTO.getLottos());

            return render(model, "winningLotto.html");
        } catch (Exception e) {
            e.printStackTrace();
            return handleError(e.getMessage());
        }
    }

    private static String result(Request request, Response response) {
        try {
            Map<String, Object> model = new HashMap<>();

            LottoResultDTO lottoResultDTO = LottoResultService.getInstance().createResult(
                    request.session().attribute("round"),
                    request.queryParams("winningNums"),
                    request.queryParams("bonusNum"),
                    request.session().attribute("lottos")
            );

            model.put("lottoResult", lottoResultDTO);
            return render(model, "result.html");
        } catch (Exception e) {
            e.printStackTrace();
            return handleError(e.getMessage());
        }
    }

    private static String history(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(request.queryParams("round"));
        try {
            model.put("history", LottoHistoryService.getInstance().historyOf(round));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return render(model, "history.html");
    }

    private static String list(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        try {
            JdbcTemplate jdbcTemplate = JdbcTemplate.getInstance(DBConnection.getConnection());
            List<Integer> rounds = LottoRoundDAO.getInstance(jdbcTemplate).selectLottoRounds();
            model.put("rounds", rounds);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return render(model, "list.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static String handleError(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", message);
        return render(model, "error.html");
    }

}
