package domain.lotto;

import domain.result.LottoRank;
import domain.result.LottoResult;
import domain.result.WinningResult;

import java.util.*;
import java.util.stream.Collectors;

public class LottoBundle {
    private final List<Lotto> lottoBundle;

    private LottoBundle(final List<Lotto> lottoBundle) {
        this.lottoBundle = new ArrayList<>(lottoBundle);
    }

    public static LottoBundle of(final List<List<Integer>> lottoNumberBundle) {
        return new LottoBundle(lottoNumberBundle.stream()
                .map(numbers -> Lotto.of(numbers))
                .collect(Collectors.toList()));
    }

    public LottoResult checkResult(final WinningResult winningResult) {
        final Map<LottoRank, Integer> lottoResult = new HashMap<>();
        for (Lotto lotto : this.lottoBundle) {
            LottoRank lottoRank = checkSingleLottoRank(lotto, winningResult);
            lottoResult.put(lottoRank, lottoResult.getOrDefault(lottoRank, 0) + 1);
        }
        return new LottoResult(lottoResult);
    }

    private LottoRank checkSingleLottoRank(final Lotto lotto, final WinningResult winningResult) {
        final int matchNumberResult = winningResult.matchWinningLotto(lotto);
        final boolean matchBonusBallResult = winningResult.matchBonusBall(lotto);
        final LottoRank lottoRank = LottoRank.findLottoRank(matchNumberResult, matchBonusBallResult);
        return lottoRank;
    }

    public int countNumberOfLotto() {
        return this.lottoBundle.size();
    }

    public List<Lotto> getLottoBundle() {
        return Collections.unmodifiableList(lottoBundle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoBundle that = (LottoBundle) o;
        return Objects.equals(lottoBundle, that.lottoBundle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoBundle);
    }
}
