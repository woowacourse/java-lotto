package lotto;

import lotto.controller.Controller;

import static spark.Spark.*;

public class WebUILottoApplication {
        public static void main(String[] args) {
                port(8080);

                staticFiles.location("/static");

                post("/", Controller.home());
                post("/round-result", Controller.roundResult());
                get("/eachResult", Controller.eachResult());
                post("/payment", Controller.pay());
                post("/manual-lotto-number", Controller.manualNumber());
                post("/purchase-lottos", Controller.purchaseLottos());
                post("/winStatsAndYield", Controller.result());
                exception(Exception.class, Controller.error());
        }


}
