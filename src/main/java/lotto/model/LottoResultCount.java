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

    public void resultLotto(Tickets tickets, WinLottoNumbers winLottoNumbers) {
        for (Ticket ticket : tickets.getTickets()) {
            int count = winLottoNumbers.matchCount(ticket);
            RankType rankType = RankType.findLottoResult(count,
                ticket.isCountFiveAndHasBonusBall(count, winLottoNumbers.getBonusBallNumber()));
            updateResultCount(rankType);
        }
    }

    public void updateResultCount(RankType rankType) {
        lottoResultCount.put(rankType, lottoResultCount.get(rankType) + 1);
    }

    public int getResultCount(RankType rankType) {
        return lottoResultCount.get(rankType);
    }
}
