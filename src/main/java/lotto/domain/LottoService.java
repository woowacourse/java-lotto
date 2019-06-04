package lotto.domain;

import java.util.List;

public class LottoService {
    private final RandomNumbersGenerator generator;
    private final LottoMachine lottoMachine;
    private final Lottos lottos;

    public LottoService(final int money) {
        generator = RandomNumbersGenerator.of(LottoNumber.MIN_NUMBER, LottoNumber.MAX_NUMBER, Lotto.LOTTO_SIZE);
        lottoMachine = new LottoMachine(money);
        lottos = new Lottos();
    }

    public void buy(final List<Integer> numbers) {
        Lotto lotto = lottoMachine.buy(numbers);
        lottos.add(lotto);
    }

    public void buyRandom() {
        final List<Integer> randomNumbers = generator.generate();
        buy(randomNumbers);
    }

    public boolean canBuy() {
        return lottoMachine.isRemainMoney();
    }

    public LottoGameResult gameResultOf(final WinningLotto winningLotto) {
        return LottoGameResult.of(lottos.getLottos(), winningLotto);
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
