package lotto;

import lotto.database.RoundInformationService;
import lotto.domain.*;
import lotto.domain.generator.LottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.utils.NumbersSplitter;
import lotto.view.OutputViewFactory;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
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
            PurchaseInformation purchaseInformation = setUpPurchaseInformation(
                    req.queryParams("money"),
                    req.queryParams("manualLottoCount"));
            registerManualLottosNumbers(purchaseInformation, req);
            Lottos lottos = LottoMachine.buyLottos(purchaseInformation);

            req.session(true);
            req.session().attribute("lottos", lottos);

            Map<String, Object> model = new HashMap<>();
            model.put("purchaseMessage", OutputViewFactory.outputLottosPurchaseMessage(purchaseInformation));
            model.put("lottos", OutputViewFactory.outputLottos(lottos));
            return render(model, "purchase-result.html");
        });

        post("/lotto/result", (req, res) -> {
            Lottos lottos = req.session().attribute("lottos");

            WinningInformation winningInformation =
                    setUpWinningInformation(req.queryParams("winningNumber"), req.queryParams("bonusBall"));
            LottoGame lottoGame = new LottoGame(winningInformation);
            LottoResult lottoResult = lottoGame.play(lottos);

            Map<String, Object> model = new HashMap<>();
            model.put("result", OutputViewFactory.outputResult(lottoResult));
            model.put("yieldMessage", OutputViewFactory.outputYield(lottoResult));

            RoundInformationService.saveRoundInformation(lottos, winningInformation);
            return render(model, "winning-result.html");
        });

        get("/round/select", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "round-select.html");
        });

        post("/round/result", (req, res) -> {
            int round = Integer.parseInt(req.queryParams("round"));
            Map<String, Object> model = RoundInformationService.loadRoundInformation(round);
            return render(model, "round-result.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", exception.getMessage());
            response.body(render(model, "error.html"));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static PurchaseInformation setUpPurchaseInformation(String money, String manualLottoCount) {
        LottoCount lottoCount = new LottoCount(Integer.parseInt(manualLottoCount), new Money(Integer.parseInt(money)));
        return new PurchaseInformation(lottoCount);
    }

    private static void registerManualLottosNumbers(PurchaseInformation purchaseInformation, Request req) {
        if (!purchaseInformation.hasManualLottos()) {
            return;
        }

        for (String lottoNumber : req.queryParams("manualLottoNumber").split("\n")) {
            purchaseInformation.addManualLottoNumbers(lottoNumber);
        }
    }

    private static WinningInformation setUpWinningInformation(String winningNumber, String bonusBall) {
        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance(NumbersSplitter.split(winningNumber));
        LottoNumbers winningNumbers = manualLottoNumbersGenerator.generate();
        LottoNumber bonusNumber = LottoNumber.valueOf(Integer.parseInt(bonusBall));
        return new WinningInformation(winningNumbers, bonusNumber);
    }
}
