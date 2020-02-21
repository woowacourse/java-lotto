package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultCalculator {
    private static final int MONEY_PER_LOTTO = 1000;

    public static ResultsDTO getResults(Lottos lottos, WinningLotto winningLotto) {
        List<WinningInfo> results = calculateResults(lottos, winningLotto);
        int totalEarning = getTotalEarning(results);
        int earningRate = getEarningRate(totalEarning, results.size());
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

    public static int getTotalEarning(List<WinningInfo> results) {
        return results.stream()
                .map(result -> result.getWinningPrice())
                .collect(Collectors.summingInt(Integer::intValue));
    }

    public static int getEarningRate(int totalEarning, int lottoSize) {
        return totalEarning / (lottoSize * MONEY_PER_LOTTO);
    }
}
