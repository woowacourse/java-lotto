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
            try {
                Map<String, Object> model = new HashMap<>();
                PurchaseInformation purchaseInformation = setUpPurchaseInformation(
                        req.queryParams("money"),
                        req.queryParams("manualLottoCount"));
                model.put("purchaseMessage", OutputViewFactory.outputLottosPurchaseMessage(purchaseInformation));

                registerManualLottosNumbers(purchaseInformation, req);
                Lottos lottos = LottoMachine.buyLottos(purchaseInformation);
                model.put("lottos", OutputViewFactory.outputLottos(lottos));

                WinningInformation winningInformation =
                        setUpWinningInformation(req.queryParams("winningNumber"), req.queryParams("bonusBall"));
                LottoGame lottoGame = new LottoGame(winningInformation);
                LottoResult lottoResult = lottoGame.play(lottos);
                model.put("result", OutputViewFactory.outputResult(lottoResult));
                model.put("yieldMessage", OutputViewFactory.outputYield(lottoResult));

                RoundInformationService.saveRoundInformation(lottos, winningInformation);
                return render(model, "result.html");
            } catch (NumberFormatException e) {
                Map<String, Object> model = new HashMap<>();
                model.put("message", "잘못된 수가 입력되었습니다.");
                return render(model, "error.html");
            } catch (Exception e) {
                Map<String, Object> model = new HashMap<>();
                model.put("message", e.getMessage());
                return render(model, "error.html");
            }
        });

        get("/round-select", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "round-select.html");
        });

        post("/round-result", (req, res) -> {
            try {
                int round = Integer.parseInt(req.queryParams("round"));
                Map<String, Object> model = RoundInformationService.loadRoundInformation(round);
                return render(model, "round-result.html");
            } catch (Exception e) {
                Map<String, Object> model = new HashMap<>();
                model.put("message", e.getMessage());
                return render(model, "error.html");
            }
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
