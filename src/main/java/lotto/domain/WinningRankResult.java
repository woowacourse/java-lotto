package lotto.domain;

import java.util.*;

public class WinningRankResult {
    private Map<WinningValue, Integer> winningValueResult = new HashMap<>();

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
        List<Integer> tempRank = new ArrayList<>();
        for (Lotto lotto : lottos) {
            WinningValue winningValue = WinningValue.findWinningValue(
                    checkCorrectNumber(lotto, winningLotto),
                    isBonusNumberContain(lotto, winningLotto));
            winningValueResult.put(winningValue, winningValueResult.get(winningValue) + 1);
        }
    }

    public Map<WinningValue, Integer> getWinningValueResult() {
        return winningValueResult;
    }

}
