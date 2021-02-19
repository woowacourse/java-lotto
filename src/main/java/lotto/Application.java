package lotto;

import lotto.domain.Lottos;

public class Application {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = lottoMachine.purchase();
        lottoMachine.seeResults(lottos);
    }
}
