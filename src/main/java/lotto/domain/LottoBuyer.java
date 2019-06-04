package lotto.domain;

import java.util.List;

public class LottoBuyer {
    private final RandomNumbersGenerator generator;
    private final LottoMachine lottoMachine;
    private final Lottos lottos;

    public LottoBuyer(final int money) {
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

    public LottoGameResult resultOf(final Lotto lotto, final LottoNumber bonusNum) {
        WinningLotto winningLotto = WinningLotto.of(lotto, bonusNum);
        return LottoGameResult.of(lottos.getContents(), winningLotto);
    }

    public LottosDto getLottos() {
        return LottosDto.of(lottos.getContents());
    }
}
