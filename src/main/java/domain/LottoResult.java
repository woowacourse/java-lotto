package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int SETUP_VALUE = 0;
    private static final int SINGLE_LOTTO_GAME_COST = 1000;

    public Map<LottoRank, Integer> checkResult(final LottoBundle lottoBundle, final WinningLotto winningLotto) {
        final Map<LottoRank, Integer> lottoResult = new HashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> lottoResult.put(lottoRank, SETUP_VALUE));

        for (Lotto lotto : lottoBundle.getLottoBundle()) {
            LottoRank lottoRank = checkSingleLottoRank(lotto, winningLotto);
            lottoResult.put(lottoRank, lottoResult.get(lottoRank) + 1);
        }

        return Collections.unmodifiableMap(lottoResult);
    }

    private LottoRank checkSingleLottoRank(final Lotto lotto, final WinningLotto winningLotto) {
        final int matchNumberResult = winningLotto.matchWinningLotto(lotto);
        final boolean matchBonusBallResult = winningLotto.matchBonusBall(lotto);
        final LottoRank lottoRank = LottoRank.findLottoRank(matchNumberResult, matchBonusBallResult);
        return lottoRank;
    }

    public double checkProfitRate(final Map<LottoRank, Integer> gameResult) {
        int moneySpent = 0;
        int moneyGain = 0;
        for (LottoRank lottoRank : LottoRank.values()) {
            moneySpent += gameResult.get(lottoRank) * SINGLE_LOTTO_GAME_COST;
            moneyGain += gameResult.get(lottoRank) * lottoRank.getPrizeMoney();
        }

        return ((double)moneyGain / (double)moneySpent);
    }
}
