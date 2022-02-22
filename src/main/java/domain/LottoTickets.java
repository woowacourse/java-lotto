package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public int size() {
        return lottoTickets.size();
    }

    public Map<LottoRank, Integer> createRanks(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        Map<LottoRank, Integer> ranks = new HashMap<>();
        Arrays.stream(LottoRank.values())
                .forEach(rank -> ranks.put(rank, 0));

        lottoTickets.forEach(lottoTicket -> {
                    ranks.merge(lottoTicket.rank(winningNumbers, bonusNumber), 1, Integer::sum);
                });

        return ranks;
    }
}
