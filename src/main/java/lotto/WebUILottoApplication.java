package lotto;

import lotto.domain.lotto.NumberOfCustomLotto;
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
        Map<String, Object> model = new HashMap<>();

        get("/home", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_home.html");
        });

        get("/rule", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_rule.html");
        });

        get("/contact", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_contact.html");
        });

        get("/start", (req, res) -> {
            model.clear();
            return render(model, "view/lotto_buy.html");
        });

        get("/purchase", (req, res) -> {
            try {
                Price price = new Price(req.queryParams("price"));
                data.setPrice(price);
                model.put("amount", price.getNumberOfLotto());
                return render(model, "view/lotto_manual_amount.html");
            } catch (Exception e) {
                model.put("error_price", e.getMessage());
                return render(model, "view/lotto_buy.html");
            }
        });

        get("/manual_amount", (req, res) -> {
            try {
                NumberOfCustomLotto number = new NumberOfCustomLotto(req.queryParams("manual_amount"), (int) model.get("amount"));
                data.setNumberOfCustomLotto(number);
                model.put("manual_amount", number.getNumberOfCustomLotto());
                model.put("auto_amount", ((int) model.get("amount")) - number.getNumberOfCustomLotto());
                return render(model, "view/lotto_manual.html");
            } catch (Exception e) {
                model.put("error_amount", e.getMessage());
                return render(model, "view/lotto_manual_amount.html");
            }
        });

//        post("/users", (request, response) -> {
//
//            Map<String, Object> model = new HashMap<>();
//            model.put("users", users);
//
//            return render(model,"result.html");
//        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
