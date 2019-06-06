package lotto;

import lotto.domain.PurchaseAmount;
import lotto.domain.game.Count;
import lotto.domain.game.ManualCount;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static WebUILottoData webUILottoData = new WebUILottoData();

    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/purchase", (req, res) -> {
            PurchaseAmount purchaseAmount = PurchaseAmount.of(Integer.parseInt(req.queryParams("amount")));
            webUILottoData.setPurchaseAmount(purchaseAmount);
            Map<String, Object> model = new HashMap<>();
            model.put("count", String.valueOf(purchaseAmount.ofCount()));
            return render(model, "manual.html");
        });

        post("/manual", (req, res) -> {
            Count totalCount = new Count(webUILottoData.getPurchaseAmount());
            webUILottoData.setTotalCount(totalCount);
            ManualCount manualCount = ManualCount.is(Integer.parseInt(req.queryParams("manual")), totalCount);
            webUILottoData.setManualCount(manualCount);
            Map<String, Object> model = new HashMap<>();
            model.put("count", String.valueOf(webUILottoData.getPurchaseAmount().ofCount()));
            model.put("manual", manualCount.toString());
            return render(model, "numbers.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
