package lotto.domain;

import java.util.*;

public class ResultCalculator {
    public static List<WinningInfo> computeResults(Lottos lottos, WinningLotto winningLotto) {
        List<WinningInfo> results = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = winningLotto.computeMatchCount(lotto);
            boolean hasBonus = lotto.hasLottoNumber(winningLotto.getBonusNumber());
            results.add(WinningInfo.valueOf(matchCount, hasBonus));
        }
        return results;
    }

    public static long computeTotalEarning(List<WinningInfo> results) {
        return results.stream()
                .map(WinningInfo::getWinningPrice)
                .mapToLong(Integer::intValue)
                .sum();
    }

    public static long computeEarningRate(long totalEarning, int lottoCount) {
        return totalEarning / (lottoCount * Lottos.PRICE_PER_LOTTO);
    }

    // 로또의 등수 별로 몇 개의 로또가 당첨되었는지 계산
    public static Map<WinningInfo, Integer> computeTotalMatchCount(List<WinningInfo> results) {
        Map<WinningInfo, Integer> map = new HashMap<>();
        for (WinningInfo winningInfo : results) {
            map.compute(winningInfo, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return map;
    }
}
