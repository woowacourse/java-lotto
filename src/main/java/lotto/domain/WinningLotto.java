package lotto.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class WinningLotto {

    private static final String DUPLICATE_EXCEPTION_MESSAGE = "당첨 번호와 보너스 볼이 중복되었습니다.";

    private final Lotto winningLotto;
    private final Ball bonusBall;

    public WinningLotto(Lotto winningLotto, Ball bonusBall) {
        validateDuplication(winningLotto, bonusBall);
        this.winningLotto = winningLotto;
        this.bonusBall = bonusBall;
    }

    private void validateDuplication(Lotto winningLotto, Ball bonusBall) {
        if (winningLotto.contains(bonusBall)) {
            throw new IllegalArgumentException(DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public TotalResult getResult(Lottos lottos) {
        Map<LottoRank, Integer> result = getInitialResult();
        for (Lotto lotto : lottos) {
            putLottoRankResult(result, lotto);
        }
        return new TotalResult(new LottoResult(result), lottos.getCount());
    }

    private void putLottoRankResult(Map<LottoRank, Integer> result, Lotto lotto) {
        int matchCount = lotto.countCommonBalls(winningLotto);
        if (!LottoRank.isPrizeCount(matchCount)) {
            return;
        }
        LottoRank rank = getLottoRank(lotto, matchCount);
        result.put(rank, result.get(rank) + 1);
    }

    private LottoRank getLottoRank(Lotto lotto, int matchCount) {
        LottoRank rank = LottoRank.getRank(matchCount, lotto.contains(bonusBall));
        return rank;
    }

    private Map<LottoRank, Integer> getInitialResult() {
        Map<LottoRank, Integer> result = new LinkedHashMap<>();
        for (LottoRank lottoRank : LottoRank.values()) {
            result.put(lottoRank, 0);
        }
        return result;
    }
}
