package util;

import domain.Lotto;
import domain.LottoBundle;
import domain.LottoRank;
import domain.WinningLotto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LottoResultGenerator {
    private static final int SETUP_VALUE = 0;

    private LottoResultGenerator() {
    }

    public static Map<LottoRank, Integer> checkResult(final LottoBundle lottoBundle, final WinningLotto winningLotto) {
        final Map<LottoRank, Integer> lottoResult = new HashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(lottoRank -> lottoResult.put(lottoRank, SETUP_VALUE));

        for (Lotto lotto : lottoBundle.getLottos()) {
            final int matchNumberResult = winningLotto.matchWinningLotto(lotto);
            final boolean matchBonusBallResult = winningLotto.matchBonusBall(lotto);
            final LottoRank lottoRank = LottoRank.findLottoRank(matchNumberResult, matchBonusBallResult);
            lottoResult.put(lottoRank, lottoResult.get(lottoRank) + 1);
        }

        return lottoResult;
    }
}
