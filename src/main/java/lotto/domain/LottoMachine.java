package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public LottoTickets purchase(Amount amount) {
        int lottoCount = amount.calculate();

        return new LottoTickets(lottoCount, new AutoLottoNumberGenerator());
    }
}
