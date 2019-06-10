package lotto;

import lotto.domain.*;
import lotto.view.OutputView;
import lotto.WebUIView.WebUIOutputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/purchase", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "purchase.html");
        });

        post("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            PurchaseInformation purchaseInformation = setUpPurchaseInformation(
                    req.queryParams("money"),
                    req.queryParams("manualLottoCount"));
            List<String> manualLottoNumber = Arrays.asList(req.queryParams("manualLottoNumber").split("\n"));

            registerManualLottosNumbers(purchaseInformation, manualLottoNumber);

            Lottos lottos = purchaseLottos(purchaseInformation);

            model.put("purchaseMessage", WebUIOutputView.outputLottosPurchaseMessage(purchaseInformation));
            model.put("lottos", WebUIOutputView.outputLottos(lottos));

            return render(model, "result.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static PurchaseInformation setUpPurchaseInformation(String money, String manualLottoCount) {
        LottoCount lottoCount = new LottoCount(Integer.parseInt(manualLottoCount), new Money(Integer.parseInt(money)));
        return new PurchaseInformation(lottoCount);
    }

    private static Lottos purchaseLottos(PurchaseInformation purchaseInformation) {

        Lottos lottos = LottoMachine.buyLottos(purchaseInformation);

        OutputView.outputLottosPurchaseMessage(purchaseInformation);
        OutputView.outputLottos(lottos);
        return lottos;
    }

    private static void registerManualLottosNumbers(PurchaseInformation purchaseInformation, List<String> manualLottoNumber) {
        if (!purchaseInformation.hasManualLottos()) {
            return;
        }

        for (String lottoNumber : manualLottoNumber) {
            purchaseInformation.addManualLottoNumbers(lottoNumber);
        }
    }
}
