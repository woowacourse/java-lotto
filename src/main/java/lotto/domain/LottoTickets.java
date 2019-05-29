package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public List<Integer> getRewards(final LottoTicket rewardTicket) {
        List<Integer> rewards = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            rewards.add(lottoTicket.getSameCount(rewardTicket));
        }
        return rewards;
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
