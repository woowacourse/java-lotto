package lotto.domain;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ResultCalculator {
    private static final int MONEY_PER_LOTTO = 1000;

    public static ResultsDTO getResults(Lottos lottos, WinningLotto winningLotto) {
        ArrayList<WinningInfo> results = getResult(lottos, winningLotto);
        int totalEarning = getTotalEarning(results);
        int earningRate = getEarningRate(totalEarning, results.size());
        return new ResultsDTO(results, totalEarning, earningRate);
    }

    public static ArrayList<WinningInfo> getResult(Lottos lottos, WinningLotto winningLotto) {
        ArrayList<WinningInfo> results = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            int winningCount = getWinningCount(lotto, winningLotto);
            boolean hasBonus = getHasBonus(lotto, winningLotto);
            results.add(WinningInfo.valueOf(winningCount, hasBonus));
        }
        return results;
    }

    public static int getWinningCount(Lotto lotto, WinningLotto winningLotto) {
        return (int) lotto.getLottoNumbers()
                .stream()
                .filter(userLottoNumber -> winningLotto.lottoNumbers.stream().anyMatch(Predicate.isEqual(userLottoNumber)))
                .count();
    }

    public static boolean getHasBonus(Lotto lotto, WinningLotto winningLotto) {
        return lotto.getLottoNumbers()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(winningLotto.getBonusNumber()));
    }

    public static int getTotalEarning(ArrayList<WinningInfo> results) {
        return results.stream()
                .map(o -> o.getWinningPrice())
                .collect(Collectors.summingInt(Integer::intValue));
    }

    public static int getEarningRate(int totalEarning, int lottoSize) {
        return totalEarning / (lottoSize * MONEY_PER_LOTTO);
    }
}
