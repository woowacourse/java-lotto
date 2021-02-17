package lotto;

import lotto.controller.LottoStore;

public class WebUILottoApplication {
    public static void main(String[] args) {
        new LottoStore().process();
    }
}
