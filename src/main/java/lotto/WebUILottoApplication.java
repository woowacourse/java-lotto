package lotto;

import lotto.controller.WebLottoShop;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebUILottoApplication {

    public static void main(String[] args) {
        WebLottoShop webLottoShop = new WebLottoShop();

        staticFiles.location("/static");

        get("/winningLotto", (req, res) -> {
            String purchasePrice = req.queryParams("purchasePrice");
            String manualAmount = req.queryParams("manualAmount");
            String manualLotto = req.queryParams("manualLotto");

            Map<String, Object> model = webLottoShop.getUserTicketState(purchasePrice, manualAmount, manualLotto);
            return render(model, "winningLotto.html");
        });

        get("/winningResult", (req, res) -> {
            String winningLotto = req.queryParams("winningLotto");
            String bonusNum = req.queryParams("bonusNum");

            Map<String, Object> model = webLottoShop.getWinningResultState(winningLotto, bonusNum);
            return render(model, "winningResult.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
