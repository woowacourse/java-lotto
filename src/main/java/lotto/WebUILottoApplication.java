package lotto;

import lotto.domain.LottoCount;
import lotto.domain.Lottos;
import lotto.domain.LottosFactory;
import lotto.domain.Money;
import lotto.view.OutputConsole;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        initExceptionHandler((e) -> System.out.println("Uh-oh"));
        staticFiles.location("/static");

        post("/winningLotto", (req, res) -> {
            Money money = new Money(Integer.parseInt(req.queryParams("money")));
            LottoCount lottoCount = new LottoCount(money, Integer.parseInt(req.queryParams("numberOfManualLotto")));
            List<List<Integer>> manualLottoNumbers = new ArrayList<>();
            List<String> inputManualLottoNumbers = Arrays.asList(req.queryParams("manualLottoNumbers").split("\r\n"));
            for (int i = 0; i < lottoCount.getManualCount(); i++) {
                List<String> stringNumbers = Arrays.asList(inputManualLottoNumbers.get(i).split(","));
                List<Integer> integers = new ArrayList<>();
                for (String stringNumber : stringNumbers) {
                    integers.add(Integer.parseInt(stringNumber));
                }
                manualLottoNumbers.add(integers);
            }
            LottosFactory lottosFactory = new LottosFactory(manualLottoNumbers, lottoCount);
            Lottos lottos = lottosFactory.getLottos();

            OutputConsole.outputLotto(lottos, lottoCount);
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
