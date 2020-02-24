package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    public static final int BONUS_COUNT = 5;

    private Map<Rank, Integer> lottoResult;

    private LottoResult() {
        this.lottoResult = new HashMap<>();
        Arrays.stream(Rank.values())
            .forEach(rank -> lottoResult.put(rank, 0));
    }

    private LottoResult(Map<Rank, Integer> lottoResult) {
        this.lottoResult = lottoResult;
    }

    public static LottoResult create(LottoTickets tickets, LottoTicket winningTicket, LottoNumber bonus) {
        LottoResult lottoResult = new LottoResult();
        return new LottoResult(lottoResult.match(tickets, winningTicket, bonus));
    }

    public static String calculate(Money money, Map<Rank, Integer> result) {
        double total = 0;
        for (Rank rank : result.keySet()) {
            total += (double)rank.getPrize() * result.get(rank);
        }
        double rate = total * 100 / (money.ticketQuantity() * Money.TICKET_PRICE);
        return String.format("%.2f", rate);
    }

    private Map<Rank, Integer> match(LottoTickets tickets, LottoTicket winningTicket, LottoNumber bonus) {
        for (LottoTicket ticket : tickets) {
            int matchCount = winningTicket.compare(ticket);
            Rank rankFound = Rank.find(matchCount, ticket.contains(bonus));
            lottoResult.put(rankFound, lottoResult.get(rankFound) + 1);
        }
        return Collections.unmodifiableMap(lottoResult);
    }

    public Map<Rank, Integer> getLottoResult() {
        return lottoResult;
    }
}
