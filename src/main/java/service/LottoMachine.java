package service;

import domain.LottoRule;
import domain.Lottos;
import domain.Money;

public class LottoMachine {

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos buyLottos(Money money) {
        int ticket = money.getAmount() / LottoRule.LOTTO_PRICE.getValue();

        return lottoNumberGenerator.generateLottos(ticket);
    }
}
