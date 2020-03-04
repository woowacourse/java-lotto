package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private static final int INITIAL_NUMBER = 0;
    private final int WINNING_COUNT = 1;
    private Map<LottoRank, Integer> lottoResult = new HashMap<>();

    public LottoResult() {
        for (LottoRank lottoRank : LottoRank.values()) {
            this.lottoResult.put(lottoRank, INITIAL_NUMBER);
        }
    }

    public void checkCount(LottoTicket lottoTicket, WinNumber winNumber, BonusBall bonusBall) {
        int matchNumber = lottoTicket.matchNumber(winNumber);
        if (LottoRank.checkNoPrize(matchNumber)) {
            return;
        }

        LottoRank lottoRank = LottoRank.of(matchNumber, lottoTicket.matchesWithBonusBall(bonusBall));
        lottoResult.put(lottoRank, lottoResult.get(lottoRank) + WINNING_COUNT);
    }

    public int rankResult(LottoRank rank) {
        return lottoResult.get(rank);
    }
}
