package lotto;

import lotto.controller.LottoPurchaseController;
import lotto.service.LottoResultService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.externalLocation("src/main/resources/templates");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = LottoResultService.findPresentRound();
            req.session().attribute("round", round);
            return render(model, "index.html");
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });

        exception(RuntimeException.class, (e, req, res) -> {
            String message = null;
            try {
                message = URLEncoder.encode(e.getMessage(), "UTF-8");
            } catch (UnsupportedEncodingException error) {
                System.out.println(error.getMessage());
            }
            res.redirect("/error?message=" + message);
        });

        LottoPurchaseController.controller();
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
