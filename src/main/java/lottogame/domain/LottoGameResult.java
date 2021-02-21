package lottogame.domain;

import lottogame.domain.ticket.LottoTicketResult;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LottoGameResult {
    private final Map<Rank, Integer> lottoGameResult;

    {
        lottoGameResult = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            lottoGameResult.put(rank, 0);
        }
    }

    public LottoGameResult(final List<LottoTicketResult> winningTickets) {
        for (LottoTicketResult winingTicket : winningTickets) {
            Rank rank = Rank.getRank(winingTicket);
            lottoGameResult.put(rank, lottoGameResult.get(rank) + 1);
        }
        lottoGameResult.remove(Rank.FAIL);
    }

    public Map<Rank, Integer> toMap() {
        return Collections.unmodifiableMap(lottoGameResult);
    }

    public double getLottoGameYield(final int ticketCount) {
        return getPrizeMoneyAmount() / ticketCount;
    }

    private double getPrizeMoneyAmount() {
        double prizeMoneyAmount = 0.0;
        for (Map.Entry<Rank, Integer> winnerBoard : lottoGameResult.entrySet()) {
            prizeMoneyAmount += getPrizeMoneyByRank(winnerBoard.getKey()) * winnerBoard.getValue();
        }
        return prizeMoneyAmount;
    }

    private double getPrizeMoneyByRank(final Rank rank) {
        return rank.getPrizeMoney();
    }
}