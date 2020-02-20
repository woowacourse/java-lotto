package lotto.domain;

import java.util.*;

public class WinningRankResult {
    private Map<WinningValue, Integer> winningValueResult = new LinkedHashMap<>();

    public WinningRankResult() {
        Arrays.stream(WinningValue.values())
                .forEach(winningValue -> this.winningValueResult.put(winningValue, 0));
    }

    public static int checkCorrectNumber(Lotto lotto, WinningLotto winningLotto) {
        return (int) lotto.getLottoNumbers().stream()
                .filter(winningLotto.getLottoNumbers()::contains)
                .count();
    }

    public static boolean isBonusNumberContain(Lotto lotto, WinningLotto winningLotto) {
        return winningLotto.isBonusNumber(lotto.getLottoNumbers());
    }

    public void checkRank(List<Lotto> lottos, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            WinningValue winningValue = getWinningValue(winningLotto, lotto);
            putWinningResult(winningValue);
        }
    }

    private void putWinningResult(WinningValue winningValue) {
        winningValueResult.put(winningValue, winningValueResult.get(winningValue) + 1);
    }

    private WinningValue getWinningValue(WinningLotto winningLotto, Lotto lotto) {
        return WinningValue.findWinningValue(
                checkCorrectNumber(lotto, winningLotto),
                isBonusNumberContain(lotto, winningLotto));
    }

    public Map<WinningValue, Integer> getWinningValueResult() {
        winningValueResult.remove(WinningValue.ZERO);
        return Collections.unmodifiableMap(winningValueResult);
    }

}
