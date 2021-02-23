package lottogame.domain;

import java.util.EnumMap;
import java.util.Map;
import lottogame.domain.number.LottoWinningNumbers;
import lottogame.domain.ticket.LottoTickets;

public class LottoGame {

    private static final int INIT_COUNT = 0;

    private final LottoTickets lottoTickets;
    private final LottoWinningNumbers lottoWinningNumbers;

    public LottoGame(final LottoTickets lottoTickets, final LottoWinningNumbers lottoWinningNumbers) {
        this.lottoTickets = lottoTickets;
        this.lottoWinningNumbers = lottoWinningNumbers;
    }

    public LottoGameResult getMatchingResult() {
        return this.lottoTickets.getMatchingResult(this.lottoWinningNumbers, initMatchingResults());
    }

    private Map<Rank, Integer> initMatchingResults() {
        Map<Rank, Integer> machingResults = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            machingResults.put(rank, INIT_COUNT);
        }
        return machingResults;
    }

    public double getYield(final LottoGameResult lottoGameResult) {
        return lottoGameResult.totalWinningPrice() / lottoGameResult.totalInvestment();
    }
}
