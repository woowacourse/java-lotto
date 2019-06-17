package lotto;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(8080);
        Spark.staticFileLocation("/public");

        get("/", WebUILottoApplication::purchaseLotto);
    }

    private static String purchaseLotto(Request request, Response response) {
        try {
            Map<String, Object> model = new HashMap<>();
            return render(model, "purchaseLotto.html");
        } catch (Exception e) {
            e.printStackTrace();
            return handleError(e.getMessage());
        }
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
