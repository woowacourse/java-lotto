package lotto.domain;

import java.util.*;

public class ResultCalculator {

    public static MatchResults computeMatchResults(Lottos lottos, WinningLotto winningLotto) {
        Map<WinningInfo, Integer> matchResults = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.computeMatchCount(lotto);
            boolean hasBonus = lotto.hasLottoNumber(winningLotto.getBonusNumber());
            WinningInfo rank = WinningInfo.valueOf(matchCount, hasBonus);
            matchResults.compute(rank, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return new MatchResults(matchResults);
    }

    public static long computeTotalEarning(MatchResults results) {
        long totalEarning = Arrays.stream(WinningInfo.values())
                .mapToLong(winningInfo -> winningInfo.getWinningPrice() * results.getMatchCount(winningInfo))
                .sum();
        return totalEarning;
    }

    public static long computeEarningRate(long totalEarning, int lottoCount) {
        return totalEarning / (lottoCount * Lottos.PRICE_PER_LOTTO);
    }
}
