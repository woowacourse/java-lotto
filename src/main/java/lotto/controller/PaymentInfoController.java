package lotto.controller;

import lotto.dao.exception.DataAccessException;
import lotto.service.PaymentInfoService;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.Map;

import static lotto.controller.common.CommonController.nullable;
import static lotto.controller.common.CommonController.render;

public class PaymentInfoController {
    private static final PaymentInfoService PAYMENT_INFO_SERVICE = PaymentInfoService.getInstance();

    private PaymentInfoController() {
        throw new AssertionError();
    }

    public static String goPayment(Request request, Response response) throws SQLDataException {
        String userName = nullable(request.queryParams("user_name"));

        Map<String, Object> model = new HashMap<>();
        try {
            PAYMENT_INFO_SERVICE.insertUser(userName);
        } catch (DataAccessException e) {
            e.printStackTrace();
            model.put("errorMessage", "죄송합니다. Payment를 등록할 수 없습니다. 관리자에게 문의해주세요.");
            return render(model, "lottoNumbers.html");
        }

        model.put("name", userName);
        return render(model, "lottoPaymentInfo.html");
    }
}
