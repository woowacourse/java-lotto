package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final Lotto winningNumbers;
    private final LottoNumber lottoNumber;

    public WinningLotto(Lotto winningNumbers, LottoNumber lottoNumber) {
        this.winningNumbers = winningNumbers;
        this.lottoNumber = lottoNumber;
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
                    lotto.isBonusMatch(lottoNumber.getLottoNumber())
            ));
        }
    }

    private int matchingCount(List<LottoNumber> lottoNumbers) {
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
        return Objects.equals(lottoNumber, that.lottoNumber);
    }

    @Override
    public int hashCode() {
        int result = winningNumbers != null ? winningNumbers.hashCode() : 0;
        result = 31 * result + (lottoNumber != null ? lottoNumber.hashCode() : 0);
        return result;
    }
}
