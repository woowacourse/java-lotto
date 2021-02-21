package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }


    public List<Result> getWinningResult(List<Lotto> lottoNumbers) {
        List<Result> results = new ArrayList<>();
        for (Lotto lotto : lottoNumbers) {
            results.add(Result.decisionLottoRank(
                    matchingCount(lotto.getLottoNumbers()),
                    lotto.isBonusMatch(bonusNumber.getBonus())
            ));
        }
        return results;
    }

    private int matchingCount(List<Integer> lottoNumbers) {
        return (int) winningNumbers.getLottoNumbers().stream()
                .filter(lottoNumbers::contains)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WinningLotto)) return false;

        WinningLotto that = (WinningLotto) o;

        if (!Objects.equals(winningNumbers, that.winningNumbers))
            return false;
        return Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        int result = winningNumbers != null ? winningNumbers.hashCode() : 0;
        result = 31 * result + (bonusNumber != null ? bonusNumber.hashCode() : 0);
        return result;
    }
}
