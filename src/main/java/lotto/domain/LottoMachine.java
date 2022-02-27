package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public LottoTickets issue(Money money) {
        return new LottoTickets(money.calculateTicketCount(), new AutoLottoNumberGenerator());
    }
}
