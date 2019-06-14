package lotto;

import lotto.cotroller.*;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static final List<String> NUMBERS = Arrays.asList("one", "two", "three", "four", "five", "six");

    public static void main(String[] args) {
        WebUILottoApplication webUILottoApplication = new WebUILottoApplication();
        webUILottoApplication.run();
    }

    private void run() {
        port(8080);
        staticFileLocation("/templates");

        get("/", WebLottoController::main);

        post("/money", WebMoneyController::money);
        post("/manual", WebManualLottoController::manualLotto);
        post("/winning", WebWinningLottoController::winningLotto);
        post("/search", WebSearchController::search);

        exception(Exception.class, (e, request, response) -> {
            try {
                response.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });
    }

    public static int convertNumber(Request req, String content) {
        return Integer.parseInt(req.queryParams(content));
    }

    public static List<Integer> convertLottoNumber(Request req) {
        List<Integer> convertNumbers = new ArrayList<>();
        NUMBERS.stream().forEach(number -> {
            convertNumbers.add(Integer.parseInt(req.queryParams(number)));
        });
        Collections.sort(convertNumbers);
        return convertNumbers;
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
