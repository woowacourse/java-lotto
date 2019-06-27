package lotto;

import lotto.controller.IndexController;
import lotto.controller.LottoPurchaseController;
import lotto.controller.ResultController;
import lotto.controller.WinningLottoInputController;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final int PORT_NO = 8080;

    public static void main(String[] args) {
        port(PORT_NO);

        staticFiles.location("/static");

        get("/input", LottoPurchaseController.serveInputPage);
        get("/index", IndexController.serveIndexPage);
        get("/winninglotto", WinningLottoInputController.serveWinningLottoInputPage);
        get("/result", ResultController.serveResultPage);
        get("/result/:round", ResultController.responseResultRound);
        get("/requestRound", ResultController.responseRound);

        post("/lottoPurchase", LottoPurchaseController.buyLotto);
        post("/winningLotto", WinningLottoInputController.inputWinningLotto);
    }
}