package domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResults {
    private static final int INIT_RANK_COUNT = 0;
    private static final int RANK_COUNT = 1;

    private Map<RankType, Integer> lottoResults;

    public LottoResults() {
        this.lottoResults = new HashMap<>();
        setUpLottoResults();
    }

    private void setUpLottoResults() {
        for(RankType rankType : RankType.values()){
            lottoResults.put(rankType, INIT_RANK_COUNT);
        }
    }

    public void putLottoResults(LottoTicket lottoTicket, WinningLottoTicket winningLottoTicket) {
        RankType rankType = RankType.of(lottoTicket, winningLottoTicket);
        lottoResults.put(rankType, lottoResults.get(rankType) + RANK_COUNT);
    }

    public Map<RankType, Integer> getLottoResults() {
        return lottoResults;
    }
}
