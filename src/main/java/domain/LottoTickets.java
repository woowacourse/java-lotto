package domain;

import java.util.*;

public class LottoTickets {
    private final List<Lotto> lottoTickets = new ArrayList<>();

    public LottoTickets(List<Lotto> manualLottoTickets, List<Lotto> autoLottoTickets) {
        this.lottoTickets.addAll(manualLottoTickets);
        this.lottoTickets.addAll(autoLottoTickets);
    }

    public LottoResult countWinningLotto(WinningNumber winningNumber) {
        Map<LottoRank, Integer> rankCount = new HashMap<>();
        initRankCount(rankCount);
        for (Lotto lotto : lottoTickets) {
            LottoRank.countRank(winningNumber.countWinningMatch(lotto), winningNumber.isBonusMatch(lotto), rankCount);
        }
        LottoResult lottoResult = new LottoResult(rankCount);
        return lottoResult;
    }

    private void initRankCount(Map<LottoRank, Integer> rankCount) {
        for (LottoRank rank : LottoRank.values()) {
            rankCount.put(rank, 0);
        }
    }

    public int getTicketsSize() {
        return lottoTickets.size();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
