package lotto;

import lotto.domain.*;
import lotto.util.ConvertLottoNumber;
import lotto.view.OutputConsole;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static lotto.domain.Rank.*;
import static spark.Spark.*;

public class WebUILottoApplication {

    private static Lottos lottos;

    public static void main(String[] args) {
        initExceptionHandler((e) -> System.out.println("Uh-oh"));
        staticFiles.location("/static");

        post("/winningLotto", (req, res) -> {
            Money money = new Money(Integer.parseInt(req.queryParams("money")));
            LottoCount lottoCount = new LottoCount(money, Integer.parseInt(req.queryParams("numberOfManualLotto")));
            List<String> inputManualLottoNumbers = Arrays.asList(req.queryParams("manualLottoNumbers").split("\r\n"));
            LottosFactory lottosFactory = new LottosFactory(inputManualLottoNumbers, lottoCount);
            Lottos postLottos = lottosFactory.generateTotalLottos();
            loadLottos(postLottos);

            Map<String, Object> model = new HashMap<>();
            model.put("lottos", postLottos);
            model.put("AutoCount", lottoCount.getAutoCount());
            model.put("ManualCount", lottoCount.getManualCount());
            return render(model, "winningLotto.html");
        });

        post("/lottoResult", (req, res) -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(ConvertLottoNumber.run(req.queryParams("winningNumbers"))),
                    LottoNumber.getInstance(Integer.parseInt(req.queryParams("bonusNumber"))));
            LottoResult lottoResult = new LottoResult(winningLotto, lottos);

            Map<String, Object> model = new HashMap<>();
            model.put("first", lottoResult.getCountOfRank(FIRST));
            model.put("second", lottoResult.getCountOfRank(SECOND));
            model.put("third", lottoResult.getCountOfRank(THIRD));
            model.put("fourth", lottoResult.getCountOfRank(FOURTH));
            model.put("fifth", lottoResult.getCountOfRank(FIFTH));
            model.put("totalEarningRate", lottoResult.getEarningsRate());
            return render(model, "lottoResult.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void loadLottos(Lottos pLottos) {
        lottos = pLottos;
    }
}