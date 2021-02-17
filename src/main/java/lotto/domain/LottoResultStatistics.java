package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResultStatistics {

    private Map<LottoRank, Integer> lottoResult;

    private LottoResultStatistics(Map<LottoRank, Integer> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public static LottoResultStatistics getResultStatistics(
            final LottoTickets lottoTickets, final LottoWinner lottoWinner) {
        Map<LottoRank, Integer> lottoResult = setLottoResult();

        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            LottoRank rank = lottoTicket.getRank(lottoWinner);
            lottoResult.put(rank, lottoResult.get(rank) + 1);
        }

        return new LottoResultStatistics(lottoResult);
    }

    public static Map<LottoRank, Integer> setLottoResult() {
        Map<LottoRank, Integer> lottoResult = new HashMap<>();

        for (LottoRank value : LottoRank.values()) {
            lottoResult.put(value, 0);
        }

        return lottoResult;
    }

    public Map<LottoRank, Integer> getLottoResult() {
        return lottoResult;
    }
}
