public class LottoRankJudge {

    private final LottoNumbers winningNumbers;
    private final Integer bonusNumber;

    public LottoRankJudge(LottoNumbers winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank judge(LottoNumbers lottoNumbers) {
        int count = getMatchedCountAboutWinningNumbers(lottoNumbers);
        boolean bonusMatch = isBonusMatch(lottoNumbers);
        return LottoRank.of(count, bonusMatch);
    }

    private int getMatchedCountAboutWinningNumbers(LottoNumbers lottoNumbers) {
        return winningNumbers.getMatchedNumberCountWith(lottoNumbers);
    }

    private boolean isBonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
