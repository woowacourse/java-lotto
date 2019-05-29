package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private RandomNumbersGenerator generator;
    private LottoMachine lottoMachine;
    private final List<Lotto> lottos;

    public LottoService(final int money) {
        generator = RandomNumbersGenerator.of(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER, Lotto.LOTTO_SIZE);
        lottoMachine = new LottoMachine(money);
        lottos = new ArrayList<>();
    }

    public void buyRandom() {
        final List<Integer> numbers = generator.generate();
        lottos.add(lottoMachine.buy(numbers));
    }

    public void buy(final List<Integer> numbers) {
        lottos.add(lottoMachine.buy(numbers));
    }

    public boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    public LottoGameResult result(final List<Integer> winningLottoNumbers, final LottoNumber bonusNum) {
        WinningLotto winningLotto = WinningLotto.of(lottoMachine.buy(winningLottoNumbers), bonusNum);
        return LottoGameResult.of(lottos, winningLotto);
    }
}
