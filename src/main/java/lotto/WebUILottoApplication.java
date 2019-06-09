package lotto;

import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.domain.lottoTicket.WinningLotto;
import lotto.domain.rank.Rank;
import lotto.domain.rank.RankResult;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static final List<String> NUMBERS = Arrays.asList("one", "two", "three", "four", "five", "six");

    public static void main(String[] args) {
        WebUILottoApplication webUILottoApplication = new WebUILottoApplication();
        webUILottoApplication.run();
    }

    private void run() {
        port(8080);
        staticFileLocation("/templates");

        Map<String, Object> model = new HashMap<>();
        List<Lotto> manualLottos = new ArrayList<>();

        post("/money", (req, res) -> {
            Money money = new Money(convertNumber(req, "money"));
            model.put("money", money);
            return render(model, "lotto.html");
        });

        post("/manual", (req, res) -> {
            manualLottos.add(new Lotto(convertLottoNumber(req)));
            model.put("manual", manualLottos);
            return render(model, "lotto.html");
        });

        post("/winning", (req, res) -> {
            WinningLotto winningLotto = new WinningLotto(convertLottoNumber(req), convertNumber(req, "bonus"));
            Money userMoney = (Money) model.get("money");
            int autoNumber = userMoney.getAutoLottoNumber(manualLottos.size());
            Lottos lottos = new Lottos(manualLottos, autoNumber);
            RankResult rank = lottos.matchLottoRank(winningLotto);
            model.put("winning", winningLotto);
            model.put("lottos", lottos);
            model.put("result", rank);
            model.put("rateOfReturn", rank.rateOfReturn(userMoney.getMoney()));
            return render(model, "lotto.html");
        });
    }

    private static int convertNumber(Request req, String content) {
        return Integer.parseInt(req.queryParams(content));
    }

    private static List<Integer> convertLottoNumber(Request req) {
        List<Integer> convertNumbers = new ArrayList<>();
        NUMBERS.stream().forEach(number -> {
            convertNumbers.add(Integer.parseInt(req.queryParams(number)));
        });
        return convertNumbers;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
