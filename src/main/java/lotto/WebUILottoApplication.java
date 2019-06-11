package lotto;

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

        post("/LottoBuyCount", (req, res) -> {
            String data = callRestApiService.lottoBuyCount(req);
            return data;
        });

        post("/detailLotteries", (req, res) -> {
            String data = callRestApiService.detailLotteries(req);
            return data;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
