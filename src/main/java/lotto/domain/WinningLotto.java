package lotto.domain;

import lotto.util.LottoFactory;

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

    public List<Result> getWinningResult(LottoFactory manualLotto, LottoFactory autoLotto) {
        List<Result> results = new ArrayList<>();
        checkResult(results, manualLotto.getLottos());
        checkResult(results, autoLotto.getLottos());

        return results;
    }

    private void checkResult(List<Result> results, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            results.add(Result.decisionLottoRank(
                    matchingCount(lotto.getLottoNumbers()),
                    lotto.isBonusMatch(bonusNumber.getBonus())
            ));
        }
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
