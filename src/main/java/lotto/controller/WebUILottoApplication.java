package lotto.controller;
import lotto.Exception.InvalidCustomLottoNumberException;
import lotto.Exception.InvalidPurchaseException;
import lotto.InputValidator;
import lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.externalLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "form.html");
        });

        post("/winning", (req,res) -> {
            Map<String,Object> winnigPageModel = new HashMap<>();
            String[] array = req.queryParamsValues("autoLottoNumbers");
            Money money = req.session().attribute("money");
                if(InputValidator.isNotValidCustomLottoes(array)){
                    throw new InvalidCustomLottoNumberException("올바른 수동로또 번호를 입력해 주세요.");
                };
            Lottoes lottoes = LottoFactory.createLottoes(array,money);
            winnigPageModel.put("lottoes",lottoes.getLottoes());
            return render(winnigPageModel,"winning.html");
        });

        post("/main", (req, res) -> {
            Map<String, Object> resultModel = new HashMap<>();
            String moneyInput = req.queryParams("money");
            String customLottoCountInput = req.queryParams("customLottoCount");
            Money money = MoneyFactory.createMoney(Integer.parseInt(moneyInput));
            CustomLottoCount customLottoCount = CustomLottoCountFactory
                        .createCustomLottoCount(Integer.parseInt(customLottoCountInput),money);
            if(InputValidator.isNotValidPrice(moneyInput)
                    || InputValidator.isNotValidCustomLottoCount(customLottoCountInput,money)){
                throw new InvalidPurchaseException("올바른 구매금액이나 수동로또 개수를 입력해주세요.");
            }

            req.session().attribute("money",money);
            resultModel.put("money",money);
            resultModel.put("customLottoCount",customLottoCount);

            return render(resultModel,"result.html");
        });

        exception(InvalidCustomLottoNumberException.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });

        exception(InvalidPurchaseException.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });
    }
    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
