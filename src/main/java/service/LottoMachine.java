package service;

import domain.Lotto;
import domain.LottoRule;
import domain.Lottos;
import domain.Money;
import util.RandomGenerator;

import java.util.List;

public class LottoMachine {

    private final int ticket;

    public LottoMachine(Money money) {
        this.ticket = money.getValue() / LottoRule.LOTTO_PRICE.getValue();
    }

    public Lottos generateLottos() {
        Lottos lottos = new Lottos();
        for (int i = 0; i < ticket; i++) {
            List<Integer> randomNumbers = RandomGenerator.generateUniqueRandomNumbers(LottoRule.LOTTO_SELECTION_SIZE.getValue(), LottoRule.MIN_LOTTO_NUMBER.getValue(), LottoRule.MAX_LOTTO_NUMBER.getValue());
            Lotto lotto = new Lotto(randomNumbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    public int getTicket() {
        return ticket;
    }
}
