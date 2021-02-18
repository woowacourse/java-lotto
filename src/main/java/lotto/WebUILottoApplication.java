package lotto;

import lotto.domain.LottoMachine;

public class WebUILottoApplication {

    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.purchase();
    }
}
