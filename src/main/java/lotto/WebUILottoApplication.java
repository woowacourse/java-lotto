package lotto;

import lotto.dao.WinnerDAO;
import lotto.service.CallRestApiService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static CallRestApiService callRestApiService = new CallRestApiService();

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/showhistory", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("turn", WinnerDAO.findRecentTurn());
            return render(model, "show_history.html");
        });

        get("/makelotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "make_lotto.html");
        });

        // TODO : post -> get 변경
        post("/LottoBuyCount", (req, res) -> {
            String data = callRestApiService.lottoBuyCount(req);
            return data;
        });

        // TODO : 해당 api가 독립적으로 움직일 수 있게 변경(지금은 /LottoBuyCount api가 선행되지 않으면 불가)
        post("/detailLotteries", (req, res) -> {
            String data = callRestApiService.detailLotteries(req);
            return data;
        });

        // TODO : 해당 api가 독립적으로 움직일 수 있게 변경(지금은 /detailLotteries api가 선행되지 않으면 불가)
        post("/detailResult", (req, res) -> {
            String data = callRestApiService.detailResult(req);
            return data;
        });

        // TODO : post -> get 변경
        post("/showHistory", (req, res) -> {
            String data = callRestApiService.showHistory(req);
            return data;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
