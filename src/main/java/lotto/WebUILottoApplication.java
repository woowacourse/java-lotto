package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        port(8080);

        staticFileLocation("templates");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_home.html");
        });

        get("/lotto_rule", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_rule.html");
        });

        get("/lotto_contact", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_contact.html");
        });


//        post("/users", (request, response) -> {
//
//            Map<String, Object> model = new HashMap<>();
////            model.put("users", users);
//
//            return render(model,"result.html");
//        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
