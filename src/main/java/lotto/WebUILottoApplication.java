package lotto;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRepository;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {

        // TODO: 2019-06-12 Transaction? -> auto commit X -> last logic
        // TODO: 2019-06-12 apply DTO? (or VO?) -> setter or builder pattern
        staticFileLocation("/static");
        port(80);

        // connect -> index page
        get("/", (req, res) -> render(null, "../static/index.html"));

        // index -> payment page
        post("/inputPayment", (req, res) -> {
            String userName = req.queryParams("user_name");

            Map<String, Object> sessionMap = new HashMap<>();
            sessionMap.put("name", userName);

            req.session().attribute("user", sessionMap); // session 에 등록

            return render(null, "../static/lottoPaymentInfo.html");
        });

        // payment -> input lotto numbers page
        post("/buyLotto", (req, res) -> {
            Map<String, Object> sessionMap = getUserSessionInfo(req);

            // TODO: 2019-06-12 Validation logic?
            Payment payment = new Payment(Integer.parseInt(req.queryParams("payment")));

            int countOfManualLotto = Integer.parseInt(req.queryParams("countOfManualLotto"));
            CountOfLotto countOfLotto = new CountOfLotto(payment, countOfManualLotto);

            sessionMap.put("payment", payment);
            sessionMap.put("countOfLotto", countOfLotto);
            req.session().attribute("user", sessionMap);

            List<String> manualLotto = IntStream.rangeClosed(1, countOfManualLotto)
                    .mapToObj(i -> "로또 번호 " + i)
                    .collect(toList());

            Map<String, Object> model = new HashMap<>();
            model.put("lottoName", manualLotto);
            return render(model, "lottoNumbers.html");
        });

        // input lotto -> input winning lotto page
        post("/inputLottos", (req, res) -> {
            Map<String, Object> sessionMap = getUserSessionInfo(req);

            // Create manual lotto
            List<String> inputManualLotto = req.queryParams().stream()
                    .map(req::queryParams)
                    .collect(toList());

            // TODO: 2019-06-12 Divide method!!
            // Convert input -> Lotto
            List<Lotto> manualLottos = inputManualLotto.stream()
                    .map(WebUILottoApplication::splitInputLottoNumbers)
                    .map(list -> LottoGenerator.create(new ManualLottoGeneratingStrategy(list)))
                    .collect(toList());

            LottoRepository lottoRepository = new LottoRepository();
            lottoRepository.addAllManualLottos(manualLottos);

            // Create random Lotto & Add repository
            CountOfLotto countOfLotto = (CountOfLotto) sessionMap.get("countOfLotto");
            IntStream.rangeClosed(0, countOfLotto.getCountOfRandomLotto())
                    .forEach(i -> lottoRepository.addAutoLottos(LottoGenerator.create(new RandomLottoGeneratingStrategy())));

            sessionMap.put("lottoTickets", new LottoTickets(lottoRepository));

            req.session().attribute("user", sessionMap);

            return render(null, "winningLotto.html");
        });

        // winning lotto -> lotto result page
        post("/inputWinningLotto", (req, res) -> {
            Map<String, Object> sessionMap = getUserSessionInfo(req);

            List<Integer> inputWinningLotto = splitInputLottoNumbers(req.queryParams("winning_lotto"));
            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(inputWinningLotto));

            WinningLotto winningLotto = new WinningLotto(lotto, Integer.parseInt(req.queryParams("bonus_ball")));

            Map<String, Object> model = new HashMap<>();
            model.put("result", winningLotto.match((LottoTickets) sessionMap.get("lottoTickets")));
            return render(model, "lottoResult.html");
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static Map<String, Object> getUserSessionInfo(Request req) {
        Optional<Map<String, Object>> sessionOptional = Optional.of(req.session().attribute("user"));
        return sessionOptional.orElseThrow(IllegalArgumentException::new);
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }
}
