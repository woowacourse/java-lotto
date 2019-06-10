package lotto;

import lotto.domain.CountOfLotto;
import lotto.domain.Payment;
import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.view.InputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    private static Payment payment;
    private static CountOfLotto countOfLotto;
    private static LottoTickets lottoTickets;

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoPrice", Payment.LOTTO_PRICE);
            model.put("paymentMessage", InputView.PAYMENT_MESSAGE);
            model.put("countOfManualLottoMessage", InputView.COUNT_OF_MANUAL_LOTTO_MESSAGE);

            return render(model, "index.html");
        });

        post("/inputStep1.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoNumberMessage", InputView.LOTTO_NUMBER_MESSAGE);
            model.put("lottoNumberMinBoundary", LottoNumber.MIN_BOUNDARY);
            model.put("lottoNumberMaxBoundary", LottoNumber.MAX_BOUNDARY);
            model.put("lottoNumberSize", Lotto.LOTTO_NUMBER_SIZE);

            payment = new Payment(req.queryParams("payment"));
            countOfLotto = new CountOfLotto(payment, req.queryParams("countOfManualLotto"));
            model.put("countOfLotto", countOfLotto);
            model.put("countOfTotalLotto", payment.calculateCountOfLotto());

            return render(model, "inputStep1.html");
        });

        post("/inputStep2.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("winningLottoNumberMessage",InputView.WINNING_LOTTO_NUMBER_MESSAGE);

            LottoRepository lottoRepository = new LottoRepository();
            int countOfManualLotto = countOfLotto.getCountOfManualLotto();
            int countOfRandomLotto = countOfLotto.getCountOfRandomLotto();

            for (int i = 1; i <= countOfManualLotto; i++) {
                List<Integer> splitLottoNumbers = splitInputLottoNumbers(req.queryParams("inputManualLottoNumber1"));
                lottoRepository.register(new ManualLottoGeneratingStrategy(splitLottoNumbers));
            }

            for (int i = 1; i <= countOfRandomLotto; i++) {
                lottoRepository.register(new RandomLottoGeneratingStrategy());
            }
            lottoTickets = lottoRepository.createLottoTickets();

            return render(model, "inputStep2.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
