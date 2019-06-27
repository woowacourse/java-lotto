package lotto.controller;

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

        PAYMENT_INFO_SERVICE.insertUser(userName);

        Map<String, Object> model = new HashMap<>();
        model.put("name", userName);
        return render(model, "lottoPaymentInfo.html");
    }
}
