package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultCalculator {
    public static ResultsDTO getResults(Lottos lottos, WinningLotto winningLotto) {
        List<WinningInfo> results = calculateResults(lottos, winningLotto);
        long totalEarning = getTotalEarning(results);
        long earningRate = getEarningRate(totalEarning, results.size());
        return new ResultsDTO(results, earningRate);
    }

    public static List<WinningInfo> calculateResults(Lottos lottos, WinningLotto winningLotto) {
        List<WinningInfo> results = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            int winningCount = lotto.compare(winningLotto);
            boolean hasBonus = lotto.hasLottoNumber(winningLotto.getBonusNumber());
            results.add(WinningInfo.valueOf(winningCount, hasBonus));
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

}
