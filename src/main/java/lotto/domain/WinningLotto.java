package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Result> getWinningResult(Lottos lottos) {
        List<Result> results = new ArrayList<>();
        addResult(results, lottos.getLottos());

        return results;
    }

    private void addResult(List<Result> results, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            results.add(Result.decisionLottoRank(
                    matchingCount(lotto.getLottoNumbers()),
                    lotto.isBonusMatch(bonusNumber.getLottoNumber())
            ));
        }
    }

    private int matchingCount(List<LottoNumber> lottoNumbers) {
        return (int) winningNumbers.getLottoNumbers().stream()
                .filter(lottoNumber1 -> lottoNumbers.contains(lottoNumber1))
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
