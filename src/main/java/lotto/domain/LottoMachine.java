package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public LottoTickets purchase(Money money) {
        int lottoCount = money.calculate();

        return new LottoTickets(lottoCount, new AutoLottoNumberGenerator());
    }
}
