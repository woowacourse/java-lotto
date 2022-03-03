package domain;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;

    private final Lottos lottos;

    public LottoGame(LottoGenerator lottoGenerator) {
        this.lottos = new Lottos(lottoGenerator.generate());
    }

    public WinningChecker makeResult(WinningNumbers winningNumbers) {
        WinningChecker winningChecker = new WinningChecker(lottos, winningNumbers);
        winningChecker.check();
        return winningChecker;
    }

    public double getYield(WinningChecker winningChecker) {
        return winningChecker.sumRewards() / (LOTTO_PRICE * lottos.getSize());
    }

    public Lottos getLottos() {
        return new Lottos(lottos.getLottos());
    }
}
