package lotto.domain;

import java.util.*;

public class LottoResult {

    private Map<WinningValue, Integer> lottoResult = new LinkedHashMap<>();

    public LottoResult() {
        Arrays.stream(WinningValue.values())
                .forEach(winningValue -> this.lottoResult.put(winningValue, 0));
    }

    void analyzeRank(Set<LottoTicket> lottoTickets,WinningLotto winningLotto){
        lottoTickets.stream()
                .map(lotto -> countHit(lotto, winningLotto))
                .forEach(this::putWinningResult);
    }

    private WinningValue countHit(LottoTicket lotto, WinningLotto winningLotto) {
        int hitCount = lotto.countSameNumbers(winningLotto.getWinningLottoNumber());
        boolean hitBonus = winningLotto.isEqualToBonus(lotto.getLottoTicket());
        return WinningValue.valueOf(hitCount, hitBonus);
    }

    private void putWinningResult(WinningValue winningValue) {
        lottoResult.put(winningValue, lottoResult.get(winningValue) + 1);
    }

    public Map<WinningValue, Integer> getLottoResult() {
        return Collections.unmodifiableMap(lottoResult);
    }
}
