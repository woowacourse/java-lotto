package lotto;

import lottery.LotteryController;
import purchase.PurchaseController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.Path;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get(Path.INDEX, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
        get(Path.PURCHASE_PAGE, PurchaseController.servePurchasePage);
        get(Path.HISTORY, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "history.html");
        });
        get(Path.LOTTERY_PAGE, LotteryController.serveLotteryPage);

        post(Path.LOTTERY, LotteryController.handleLottery);
        post(Path.PURCHASE, PurchaseController.handlePurchase);

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
