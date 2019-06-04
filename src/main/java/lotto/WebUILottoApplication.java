package lotto;

import com.woowacourse.lotto.domain.Lotto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("minBuyingMoney", Lotto.UNIT_PRICE);
            return render(model, "index.html");
        });

        post("/buy", (req, res) -> {
            System.out.println("BuyingMoney: " + req.queryMap("money").value());
            System.out.println("ManualQty: " + req.queryMap("qty_manual").value());
            System.out.printf("Winning: %d, %d, %d, %d, %d, %d / %d\n",
                req.queryMap("winning_0").integerValue(),
                req.queryMap("winning_1").integerValue(),
                req.queryMap("winning_2").integerValue(),
                req.queryMap("winning_3").integerValue(),
                req.queryMap("winning_4").integerValue(),
                req.queryMap("winning_5").integerValue(),
                req.queryMap("winning_bonus").integerValue()
            );
            Map<String, Object> model = new HashMap<>();
            res.redirect("/");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
