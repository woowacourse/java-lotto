package lotto;

import lotto.domain.CountOfLotto;
import lotto.domain.Payment;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.view.InputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoPrice", Payment.LOTTO_PRICE);
            model.put("paymentMessage", InputView.PAYMENT_MESSAGE);
            model.put("countOfManualLottoMessage", InputView.COUNT_OF_MANUAL_LOTTO_MESSAGE);

            return render(model, "index.html");
        });

        post("/input.html", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("lottoNumberMessage", InputView.LOTTO_NUMBER_MESSAGE);
            model.put("lottoNumberMinBoundary", LottoNumber.MIN_BOUNDARY);
            model.put("lottoNumberMaxBoundary", LottoNumber.MAX_BOUNDARY);
            model.put("lottoNumberSize", Lotto.LOTTO_NUMBER_SIZE);

            Payment payment = new Payment(req.queryParams("payment"));
            CountOfLotto countOfLotto = new CountOfLotto(payment, req.queryParams("countOfManualLotto"));
            model.put("countOfLotto", countOfLotto);
            model.put("countOfTotalLotto", payment.calculateCountOfLotto());

            return render(model, "input.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
