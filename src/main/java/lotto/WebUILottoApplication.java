package lotto;

import lotto.domain.Money;
import lotto.domain.dao.*;
import lotto.domain.lottoTicket.*;
import lotto.domain.rank.RankResult;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
           return render(model, "lotto.html");
        });

        post("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Money money = new Money(convertNumber(req, "money"));
            req.session().attribute("money", money);
            req.session().attribute("manual", new ArrayList<Lotto>());
            return render(model, "lotto.html");
        });

        post("/manual", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Integer> lottoNumbers = convertLottoNumber(req);
            List<Lotto> manualLottos = req.session().attribute("manual");
            manualLottos.add(new Lotto(lottoNumbers));
            req.session().attribute("manual", manualLottos);
            return render(model, "lotto.html");
        });

        post("/winning", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Integer> winningLottoNumber = convertLottoNumber(req);
            int bonus = convertNumber(req, "bonus");
            WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
            Money money = req.session().attribute("money");
            Lottos lottos = getLottos(req, money);
            RankResult rank = lottos.matchLottoRank(winningLotto);

            RoundDAO.registerCount();
            LottoDAO.addTotalLottos(lottos);
            WinningDAO.addWinningLotto(winningLottoNumber, bonus);
            ResultDAO.addResult(rank, money);
            return render(model, "lotto.html");
        });

        exception(Exception.class, (e, request, response) -> {
            try {
                response.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });
    }

    private Lottos getLottos(Request req, Money money) {
        List<Lotto> manualLottos = req.session().attribute("manual");
        return new Lottos(manualLottos,
                money.getAutoLottoNumber(manualLottos.size()));
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
