package domain;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;
    private WinningChecker winningChecker;

    public LottoGame(Money money, LottoGenerator lottoGenerator) {
        this.lottos = new Lottos(lottoGenerator.generate(money.convertToAmount()));
    }

    public void makeResult(WinningNumbers winningNumbers) {
        winningChecker = new WinningChecker(lottos, winningNumbers);
        winningChecker.check();
    }

    public double getYield() {
        return winningChecker.sumRewards() / (LOTTO_PRICE * lottos.getSize());
    }

    public Lottos getLottos() {
        return lottos;
    }
}
