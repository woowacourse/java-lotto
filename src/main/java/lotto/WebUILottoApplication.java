package lotto;

import lotto.controller.Lotto;

import static spark.Spark.get;

public class WebUILottoApplication {
    public static void main(String[] args) {
        Lotto.lottoGame();
    }
}
