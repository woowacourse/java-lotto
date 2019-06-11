package lotto;

import lotto.domain.lotto.Price;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static WebUILottoData data = new WebUILottoData();

    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_home.html");
        });

        get("/rule", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_rule.html");
        });

        get("/contact", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_contact.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "view/lotto_buy.html");
        });

        get("/purchase", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Price price = new Price(req.queryParams("price"));
                data.setPrice(price);
                model.put("amount",String.valueOf(price.getNumberOfLotto()));
                return render(model, "view/lotto_manual_amount.html");
            } catch (Exception e){
                model.put("error","잘못된 입력입니다.");
                return render(model, "view/lotto_buy.html");
            }
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
