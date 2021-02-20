package lotto.domain.lotto;

import java.util.Map;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class WinningStatistics {

    private final Ranks ranks;
    private final double yield;

    public WinningStatistics(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        this.ranks = Ranks.valueOf(lottoTicket, winningNumbers);
        this.yield = (double) ranks.getTotalWinnings() / lottoTicket.getTotalLottoPrice();
    }

    public Map<Rank, Long> unwrap() {
        return ranks.unwrap();
    }

    public double getYield() {
        return yield;
    }
}