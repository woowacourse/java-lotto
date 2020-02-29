package lotto.model;

import java.util.HashMap;
import java.util.Map;

public class LottoResultCount {
    private static final int INITIAL_VALUE = 0;
    private final Map<RankType, Integer> lottoResultCount = new HashMap<>();

    {
        for (RankType rankType : RankType.values()) {
            lottoResultCount.put(rankType, INITIAL_VALUE);
        }
    }

    public void resultCount(Ticket ticket, WinLottoNumbers winLottoNumbers) {
        int count = winLottoNumbers.matchCount(ticket);
        RankType rankType = RankType.findLottoResult(count,
            ticket.isCountFiveAndHasBonusBall(count, winLottoNumbers.getBonusBallNumber()));
        lottoResultCount.put(rankType, lottoResultCount.get(rankType) + 1);
    }

    public int getResultCount(RankType rankType) {
        return lottoResultCount.get(rankType);
    }
}
