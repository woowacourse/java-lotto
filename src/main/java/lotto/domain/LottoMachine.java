package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public LottoTickets purchase(Money money) {
        return new LottoTickets(money.calculate(), new AutoLottoNumberGenerator());
    }
}
