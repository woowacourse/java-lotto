package lotto;

import lotto.domain.*;
import lotto.domain.generator.LottoNumbersGenerator;
import lotto.domain.generator.ManualLottoNumbersGenerator;
import lotto.utils.NumbersSplitter;
import lotto.view.OutputViewFactory;
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
            try {
                Map<String, Object> model = new HashMap<>();
                PurchaseInformation purchaseInformation = setUpPurchaseInformation(
                        req.queryParams("money"),
                        req.queryParams("manualLottoCount"));
                List<String> manualLottoNumber = Arrays.asList(req.queryParams("manualLottoNumber").split("\n"));
                registerManualLottosNumbers(purchaseInformation, manualLottoNumber);
                Lottos lottos = LottoMachine.buyLottos(purchaseInformation);
                model.put("purchaseMessage", OutputViewFactory.outputLottosPurchaseMessage(purchaseInformation));
                model.put("lottos", OutputViewFactory.outputLottos(lottos));

                LottoGame lottoGame = setUpLottoGame(req.queryParams("winningNumber"), req.queryParams("bonusBall"));
                LottoResult lottoResult = lottoGame.play(lottos);
                model.put("result", OutputViewFactory.outputResult(lottoResult));
                model.put("yieldMessage", OutputViewFactory.outputYield(lottoResult));

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
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static PurchaseInformation setUpPurchaseInformation(String money, String manualLottoCount) {
        LottoCount lottoCount = new LottoCount(Integer.parseInt(manualLottoCount), new Money(Integer.parseInt(money)));
        return new PurchaseInformation(lottoCount);
    }

    private static void registerManualLottosNumbers(PurchaseInformation purchaseInformation,
                                                    List<String> manualLottoNumber) {
        if (!purchaseInformation.hasManualLottos()) {
            return;
        }

        for (String lottoNumber : manualLottoNumber) {
            purchaseInformation.addManualLottoNumbers(lottoNumber);
        }
    }

    private static LottoGame setUpLottoGame(String winningNumber, String bonusBall) {
        LottoNumbersGenerator manualLottoNumbersGenerator =
                ManualLottoNumbersGenerator.getInstance(NumbersSplitter.split(winningNumber));
        LottoNumbers winningNumbers = manualLottoNumbersGenerator.generate();

        LottoNumber bonusNumber = LottoNumber.valueOf(Integer.parseInt(bonusBall));

        WinningInformation winningInformation = new WinningInformation(winningNumbers, bonusNumber);
        return new LottoGame(winningInformation);
    }
}
