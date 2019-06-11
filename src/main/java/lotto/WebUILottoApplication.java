package lotto;

import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.domain.lottoTicket.WinningLotto;
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

        List<Lotto> manualLottos = new ArrayList<>();

        post("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Money money = new Money(convertNumber(req, "money"));
            model.put("money", money);
            req.session().attribute("money", money);
            return render(model, "lotto.html");
        });

        post("/manual", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            manualLottos.add(new Lotto(convertLottoNumber(req)));
            model.put("manual", manualLottos);
            //TODO 회차에 해당 (requestMoney -> (lotto).{one ~ six})
            return render(model, "lotto.html");
        });

        post("/winning", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            WinningLotto winningLotto = new WinningLotto(convertLottoNumber(req), convertNumber(req, "bonus"));
            Money userMoney = req.session().attribute("money");
            int autoNumber = userMoney.getAutoLottoNumber(manualLottos.size());
            Lottos lottos = new Lottos(manualLottos, autoNumber);
            RankResult rank = lottos.matchLottoRank(winningLotto);
            /* TODO (winning).{1~6 / bonus} 등록하기 */
            /* TODO
            *   money 가져오기, manualLottos 크기 가져오기 => 만들 수 있는 autoLotto 갯수
            * 개수로 autoLotto 전부 가져와서 (Lotto).{1~6} 등록하기 */

            /* TODO
            *   winningLotto랑 일치하는 로또 찾기 -> (result).{1등~5등} 등록
            *   rank.totalMoney -> (result).{money} 등록
            *   rank.rateOfReturn -> (result).{yeild} 등록*/

            model.put("winning", winningLotto);
            model.put("lottos", lottos);
            model.put("result", rank);
            model.put("rateOfReturn", rank.rateOfReturn(userMoney.getMoney()));
            return render(model, "lotto.html");
        });

        exception(RuntimeException.class, (e, request, response) -> {
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
