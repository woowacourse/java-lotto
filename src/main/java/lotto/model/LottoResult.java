package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final int WINNING_COUNT = 1;
    private final int INITIAL_NUMBER = 0;
    private Map<LottoRank, Integer> lottoResult;

    public LottoResult() {
        Map<LottoRank, Integer> lottoResult = new HashMap<>();
        lottoResult.put(LottoRank.FIFTH, INITIAL_NUMBER);
        lottoResult.put(LottoRank.FOURTH, INITIAL_NUMBER);
        lottoResult.put(LottoRank.THIRD, INITIAL_NUMBER);
        lottoResult.put(LottoRank.SECOND, INITIAL_NUMBER);
        lottoResult.put(LottoRank.FIRST, INITIAL_NUMBER);
        this.lottoResult = lottoResult;
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
