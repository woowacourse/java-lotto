package lotto;

import lotto.controller.LottoController;
import lotto.controller.LottoResultController;
import lotto.controller.PaymentInfoController;
import lotto.controller.common.CommonController;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {

        setUp();

        // connect -> index page
        get("/", LottoResultController::goIndex);

        // index -> payment page
        post("/payment", PaymentInfoController::goPayment);

        // payment -> input lotto numbers page
        post("/lotto", LottoController::goSelectLotto);

        // input lotto -> lotto result page
        post("/result", LottoController::insertAndGoResult);

        // -> lotto result page
        get("/result/:round", LottoResultController::goLottoResult);

        exception(IllegalArgumentException.class, CommonController::handlingException);
    }

    private static void setUp() {
        staticFileLocation("/");
        port(80);
    }
}
