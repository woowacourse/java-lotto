package lotto;

import lotto.controller.LottoController;
import lotto.controller.Store;

public class Application {

    public static void main(String[] args) {
        new LottoController(new Store()).run();
    }
}
