import java.util.List;

public class LottoRankJudge {

    private final List<Integer> winningNumbers;
    private final Integer bonusNumber;

    public LottoRankJudge(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public LottoRank judge(List<Integer> lottoNumbers) {
        long count = getMatchedCountAboutWinningNumbers(lottoNumbers);
        boolean bonusMatch = isBonusMatch(lottoNumbers);
        return LottoRank.of(count, bonusMatch);
    }

    private long getMatchedCountAboutWinningNumbers(List<Integer> lottoNumbers) {
        return winningNumbers.stream()
            .filter(winningNumber -> lottoNumbers.contains(winningNumber))
            .count();
    }

    private boolean isBonusMatch(List<Integer> lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
