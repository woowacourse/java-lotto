package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoManager {
    public static Map<RankType, Integer> match(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        LottoResults lottoResults = new LottoResults();
        lottoResults.putLottoResults(getLottoCounts(lottoTickets, winningLottoTicket));
        return lottoResults.getLottoResults();
    }

    private static List<LottoCount> getLottoCounts(LottoTickets lottoTickets, WinningLottoTicket winningLottoTicket) {
        List<LottoCount> lottoCounts = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            lottoCounts.add(new LottoCount(winningLottoTicket.getCorrectCount(lottoTicket), winningLottoTicket.isMatchBonusBall(lottoTicket)));
        }
        return lottoCounts;
    }
}
