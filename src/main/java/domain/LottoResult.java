package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {

    private static final int DEFAULT_VALUE = 0;

    private final Map<LottoRank, Integer> resultCount;

    public LottoResult(LottoTicket lottoTicket, WinningLotto winningLotto) {
        this.resultCount = new LinkedHashMap<>();
        for (Lotto lotto : lottoTicket.getLottos()) {
            LottoRank rank = winningLotto.countLottoRank(lotto);
            this.resultCount.put(rank, resultCount.getOrDefault(rank, 0) + 1);
        }
    }

    public long sumTotalPrice() {
        long totalPrice = DEFAULT_VALUE;
        for (LottoRank rank : resultCount.keySet()) {
            totalPrice += (long) rank.getPrice() * resultCount.get(rank);
        }

        return totalPrice;
    }

    public Map<LottoRank, Integer> getResultCount() {
        return Collections.unmodifiableMap(resultCount);
    }
}
