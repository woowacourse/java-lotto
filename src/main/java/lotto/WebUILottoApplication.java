package lotto;

import lotto.domain.*;
import lotto.domain.generator.ResultGenerator;
import lotto.utils.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;

import static lotto.domain.generator.LottoNumbersGenerator.generateLottoNumbers;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates/");



        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        post("/lotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            // 구입 금액 처리
            Money money = new Money(Integer.parseInt(req.queryParams("buy-price")));

            // 수동 번호 입력 처리
            String inputLottoNumbers = req.queryParams("lotto-numbers");
            List<String> inputManualLottos = inputLottoNumbers.equals("") ? Collections.EMPTY_LIST
                    : Arrays.stream(inputLottoNumbers.split("\n"))
                    .map(String::trim)
                    .collect(Collectors.toList());
            BoughtLottos boughtLottos = BoughtLottos.buyLottos(money.getBuyPrice(), inputManualLottos);

            // 당첨번호 처리
            WinningNumber winningNumber = new WinningNumber(new Lotto(
                    generateLottoNumbers(req.queryParams("winning-numbers"))),
                    Integer.parseInt(req.queryParams("bonus")));
            // 결과
            Result result = ResultGenerator.generateResult(boughtLottos, winningNumber);
            model.put("boughtLottos", boughtLottos);
            model.put("result", result);
            return model;
        }, new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
