package lotto;

import com.google.gson.Gson;
import lotto.domain.UserLottos;
import lotto.service.UserLottosCreator;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("templates");
        staticFiles.location("templates");
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/buyLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "buy.html");
        });

        post("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("money", req.queryParams("money"));
            model.put("manual", req.queryParams("manual"));
            return render(model, "lotto.html");
        });

        get("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lotto.html");
        });

        post("/userLotto", (req, res) -> {
            int manualCount;
            try {
                manualCount = Integer.parseInt(req.queryParams("manualCount"));
            } catch (NumberFormatException e) {
                manualCount = 0;
            }
            UserLottos userLottos = new UserLottosCreator(req.queryParams("lottoMoney"), manualCount, Arrays.asList(req.queryParamsValues("manualLottos"))).create();
            return userLottos.tickets().stream().map(ticket -> ticket.ticketNumbers().toString()).collect(Collectors.toList());
        }, gson::toJson);

        get("/test", (req, res) -> req.queryParams(), gson::toJson);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
