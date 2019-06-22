package lotto;

import lotto.controller.LottoController;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUILottoApplication {
    public static void main(String[] args) {
        LottoController lottoController = LottoController.getInstance();

        get("/", (req, res) -> lottoController.mainPage());

        post("/lotto", (req, res) -> lottoController.lottoPage(req));

        post("/winLotto", (req, res) -> lottoController.winningPage(req));

        get("/result", (req, res) -> lottoController.resultPage(req));

        get("/hits", (req, res) -> lottoController.hitsPage(req));

        post("/lottoHits", (req, res) -> lottoController.lottoHitsPage(req));
    }
}
