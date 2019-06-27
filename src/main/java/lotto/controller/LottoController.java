package lotto.controller;

import lotto.service.LottoService;
import lotto.service.PaymentInfoService;
import lotto.service.dto.PaymentInfoDto;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static lotto.controller.common.CommonController.nullable;
import static lotto.controller.common.CommonController.render;

public class LottoController {
    private static final PaymentInfoService PAYMENT_INFO_SERVICE = PaymentInfoService.getInstance();
    private static final LottoService LOTTO_SERVICE = LottoService.getInstance();

    private LottoController() {
        throw new AssertionError();
    }

    public static String goSelectLotto(Request request, Response response) throws SQLDataException {
        int payment = Integer.parseInt(nullable(request.queryParams("payment")));
        int countOfManualLotto = Integer.parseInt(nullable(request.queryParams("countOfManualLotto")));
        String name = nullable(request.queryParams("name"));
        PaymentInfoDto paymentInfoDto = createPaymentInfoDto(payment, countOfManualLotto, name);

        int round = 0;
        Map<String, Object> model = new HashMap<>();
        try {
            round = PAYMENT_INFO_SERVICE.insertPaymentInfoAndReturnKeyValue(paymentInfoDto);
        } catch (SQLException e) {
            e.printStackTrace();
            model.put("errorMessage", "죄송합니다. Payment를 등록할 수 없습니다. 관리자에게 문의해주세요.");
            return render(model, "error.html");
        }

        paymentInfoDto.setCountOfLotto(PAYMENT_INFO_SERVICE.calculateCountOfLotto(paymentInfoDto));
        model.put("lottoName", createResponseInputTag(countOfManualLotto));
        model.put("paymentInfo", paymentInfoDto);
        model.put("round", round);
        return render(model, "lottoNumbers.html");
    }

    private static PaymentInfoDto createPaymentInfoDto(int payment, int countOfManualLotto, String name) {
        PaymentInfoDto paymentInfoDto = new PaymentInfoDto();
        paymentInfoDto.setPayment(payment);
        paymentInfoDto.setManual(countOfManualLotto);
        paymentInfoDto.setName(name);
        return paymentInfoDto;
    }

    public static String insertAndGoResult(Request request, Response response) throws SQLDataException {
        int countOfLotto = Integer.parseInt(nullable(request.queryParams("countOfLotto")));
        int round = Integer.parseInt(nullable(request.queryParams("round")));
        String name = nullable(request.queryParams("name"));

        String[] inputLottos = request.queryParamsValues("lotto_number");
        String inputWinningLotto = nullable(request.queryParams("winning_lotto"));
        String inputBonusBall = nullable(request.queryParams("bonus_ball"));

        // TODO: 2019-06-27 Create Dto class...
        try {
            LOTTO_SERVICE.insertLottoAndResult(inputLottos, countOfLotto, inputWinningLotto, inputBonusBall, round, name);
        } catch (SQLException e) {
            e.printStackTrace();
            Map<String, Object> model = new HashMap<>();
            model.put("errorMessage", "죄송합니다. Lotto를 등록할 수 없습니다. 관리자에게 문의해주세요.");
            return render(model, "lottoNumbers.html");
        }

        response.redirect("/result/" + round);

        // TODO How to process return?
        return render(null, "lottoResult.html");
    }

    private static List<String> createResponseInputTag(int countOfManualLotto) {
        return IntStream.rangeClosed(1, countOfManualLotto)
                .mapToObj(i -> "로또 번호 " + i)
                .collect(toList());
    }

}
