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
        return getPriceAmount() / ticketCount;
    }

    private double getPriceAmount() {
        double priceAmount = 0.0;
        for (Map.Entry<Rank, Integer> rank : lottoGameResult.entrySet()) {
            priceAmount += rank.getKey().getPrice() * rank.getValue();
        }
        return priceAmount;
    }
}