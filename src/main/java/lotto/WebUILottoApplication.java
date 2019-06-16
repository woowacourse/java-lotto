package lotto;

import lotto.controller.PurchasingLottoController;
import lotto.controller.LottoGameController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", LottoGameController.index);

        path("/purchase", () -> {
            get("", PurchasingLottoController.purchasingTickets);
            post("/ticket", PurchasingLottoController.purchasedTickets);
        });

        path("/win", () -> {
            post("/input", LottoGameController.addWinningLotto);
            get("/result", LottoGameController.latestGameResult);
            get("/result/:week", LottoGameController.gameResultByWinningLottoId);
            get("/select", LottoGameController.allWeeksOfGame);
        });
    }
}
