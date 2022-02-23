package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public LottoTickets purchase(String price) {
        Amount amount = Amount.create(price);
        int lottoCount = amount.calculate();

        return new LottoTickets(lottoCount, new AutoLottoNumberGenerator());
    }
}
