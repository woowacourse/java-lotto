package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    private final static int LOTTO_PRICE = 1000;

    public LottoTickets issue(Money money) {
        return new LottoTickets(money.getProductCount(LOTTO_PRICE), new AutoLottoNumberGenerator());
    }
}
