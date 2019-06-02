package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LottoTickets {
    private static final int REWARD_DEFAULT_VALUE = 0;
    private static final int REWARD_INCREASE_VALUE = 1;

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public Map<LottoRank, Integer> getRewards(WinningLotto winningLotto) {
        Map<LottoRank, Integer> rewards = new TreeMap<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            LottoRank lottoRank = winningLotto.getRank(lottoTicket);
            rewards.put(lottoRank, rewards.getOrDefault(lottoRank, REWARD_DEFAULT_VALUE) + REWARD_INCREASE_VALUE);
        }
        return rewards;
    }

    public List<LottoTicket> getTickets() {
        return lottoTickets;
    }
}
