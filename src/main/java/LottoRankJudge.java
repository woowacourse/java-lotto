public class LottoRankJudge {

    private final LottoNumbers winningNumbers;
    private final Integer bonusNumber;

    public LottoRankJudge(LottoNumbers winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank judge(LottoNumbers lottoNumbers) {
        long count = getMatchedCountAboutWinningNumbers(lottoNumbers);
        boolean bonusMatch = isBonusMatch(lottoNumbers);
        return LottoRank.of(count, bonusMatch);
    }

    private long getMatchedCountAboutWinningNumbers(LottoNumbers lottoNumbers) {
        return winningNumbers.stream()
            .filter(winningNumber -> lottoNumbers.contains(winningNumber))
            .count();
    }

    private boolean isBonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
