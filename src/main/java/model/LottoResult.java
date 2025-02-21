package model;

public class LottoResult {
    private final Lotto lotto;
    private PrizeTier prizeTier;

    public LottoResult(Lotto lotto) {
        this.lotto = lotto;
    }

    public void evaluateLotto(WinningNumbers winningNumbers) {
        int matchedCount = winningNumbers.getMatchCount(lotto);
        boolean isBonusMatched = winningNumbers.isBonusMatched(lotto);
        prizeTier = PrizeTier.getTier(matchedCount, isBonusMatched);
    }

    public Boolean isTierMatched(PrizeTier prizeTier) {
        return this.prizeTier == prizeTier;
    }

    public int getPrize() {
        return prizeTier.getPrize();
    }
}
