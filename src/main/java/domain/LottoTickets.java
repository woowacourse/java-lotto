package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private static final int DEFAULT_VALUE = 0;
    private static final int INCREASE_VALUE = 1;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public Map<LottoRank, Integer> createRanks(LottoTicketNumbers winningNumbers, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> ranks = new HashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(rank -> ranks.put(rank, DEFAULT_VALUE));

        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.merge(lottoTicket.rank(winningNumbers, bonusNumber), INCREASE_VALUE, Integer::sum);
        }

        return ranks;
    }
}
