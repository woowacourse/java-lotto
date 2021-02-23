package lotto.domain;

import java.util.EnumMap;
import java.util.List;

public class LottoResultMachine {
    public static EnumMap<Rank, Integer> confirmResult(LottoTickets lottoTickets, WinningTicket winningTicket) {
        final List<LottoTicket> tickets = lottoTickets.toList();
        final EnumMap<Rank, Integer> result = new EnumMap<>(Rank.class);

        tickets.forEach(ticket -> updateResult(result, winningTicket.getRankOfLottoResult(ticket)));
        return result;
    }

    private static void updateResult(EnumMap<Rank, Integer> result, Rank rank) {
        if (result.containsKey(rank)) {
            result.replace(rank, result.get(rank) + 1);
            return;
        }
        result.put(rank, 1);
    }
}
