package lotto.domain.result;

import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.WinningLotto;

import java.util.*;

public class LottoResult {
    private static final int RATE = 100;

    private Map<WinningValue, Integer> lottoResult = new LinkedHashMap<>();

    public LottoResult() {
        Arrays.stream(WinningValue.values())
                .forEach(winningValue -> this.lottoResult.put(winningValue, 0));
    }

    public void calculateLottoResult(List<Lotto> lottos, WinningLotto winningLotto) {
        lottos.stream()
                .map(lotto -> findWinningValue(lotto, winningLotto))
                .forEach(this::putWinningResult);
    }

    private WinningValue findWinningValue(Lotto lotto, WinningLotto winningLotto) {
        int hitCount = lotto.countCorrectNumber(winningLotto);
        boolean hitBonus = winningLotto.isBonusNumber(lotto);
        return WinningValue.valueOf(hitCount, hitBonus);
    }

    private void putWinningResult(WinningValue winningValue) {
        lottoResult.put(winningValue, lottoResult.get(winningValue) + 1);
    }

    public int calculateRewardRate(int money, Map<WinningValue, Integer> winningValueResult) {
        return winningValueResult.entrySet()
                .stream()
                .mapToInt(result ->
                        result.getKey().getReward()
                                * result.getValue())
                .sum() / money * RATE;
    }

    public Map<WinningValue, Integer> getLottoResult() {
        lottoResult.remove(WinningValue.ZERO);
        return Collections.unmodifiableMap(lottoResult);
    }
}
