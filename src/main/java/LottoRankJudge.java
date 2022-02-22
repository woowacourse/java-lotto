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

        if (isSecondPrize(lottoNumbers, count)) {
            return LottoRank.SECOND;
        }

        return LottoRank.FIRST;
    }

    private long getMatchedCountAboutWinningNumbers(List<Integer> lottoNumbers) {
        return winningNumbers.stream()
            .filter(winningNumber -> lottoNumbers.contains(winningNumber))
            .count();
    }

    private boolean isSecondPrize(List<Integer> lottoNumbers, long count) {
        return count == 5 && lottoNumbers.contains(bonusNumber);
    }
}
