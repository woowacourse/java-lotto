package lotto.controller;
import lotto.Exception.InvalidCustomLottoNumberException;
import lotto.Exception.InvalidPurchaseException;
import lotto.Exception.InvalidWinningLottoException;
import lotto.InputValidator;
import lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            Map<String,Object> model = new HashMap<>();
            String[] array = req.queryParamsValues("autoLottoNumbers");
            Money money = req.session().attribute("money");
                if(InputValidator.isNotValidCustomLottoes(array)){
                    throw new InvalidCustomLottoNumberException("올바른 수동로또 번호를 입력해 주세요.");
                };
            Lottoes lottoes = LottoFactory.createLottoes(array,money);
            model.put("lottoes",lottoes.getLottoes());
            req.session().attribute("lottoes",lottoes);
            return render(model,"winning.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> winnigNumberInput = Arrays.asList(req.queryParams("winningNumber").split(","));
            String bonusBall = req.queryParams("bonusBall");
            Lotto lotto = LottoFactory.createLotto(winnigNumberInput);
            if(InputValidator.isNotValidLotto(winnigNumberInput)
                    || InputValidator.isNotValidWinningLotto(lotto,bonusBall)){
                throw new InvalidWinningLottoException("올바른 당첨번호를 입력해 주세요.");
            }
            WinningLotto winningLotto = LottoFactory.createWinningLotto(lotto,Integer.parseInt(bonusBall));
            Result result = ResultFactory.createResult();
            Lottoes lottoes = req.session().attribute("lottoes");
            result.calculateResult(lottoes, winningLotto);

           for(Rank rank : Rank.values()){
               if(rank == Rank.NONE){
                   continue;
               }
                model.put(rank.name(),result.getMatchlottoCountPerRank(rank));
            }
            model.put("rate",result.getRate(req.session().attribute("money")));
            return render(model,"finalResult.html");
        });


        post("/main", (req, res) -> {
            Map<String, Object> resultModel = new HashMap<>();
            String moneyInput = req.queryParams("money");
            String customLottoCountInput = req.queryParams("customLottoCount");

            if(InputValidator.isNotValidPrice(moneyInput)){
                throw new InvalidPurchaseException("올바른 구매금액을 입력해주세요.");
            }
            Money money = MoneyFactory.createMoney(Integer.parseInt(moneyInput));
            if(InputValidator.isNotValidCustomLottoCount(customLottoCountInput,money)){
                throw new InvalidPurchaseException("올바른 수동로또 개수를 입력해주세요.");
            }

            CustomLottoCount customLottoCount = CustomLottoCountFactory
                        .createCustomLottoCount(Integer.parseInt(customLottoCountInput),money);
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

        exception(InvalidWinningLottoException.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });
    }
    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
