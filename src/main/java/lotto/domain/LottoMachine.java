package lotto.domain;

import lotto.domain.generator.AutoLottoNumberGenerator;

public class LottoMachine {

    public void purchase(String price) {
        Amount amount = Amount.create(price);
        int lottoCount = amount.calculate();

        LottoTickets lottoTickets = new LottoTickets(lottoCount, new AutoLottoNumberGenerator());
    }
}
