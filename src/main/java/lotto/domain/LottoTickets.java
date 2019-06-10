package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @Override
    public String toString() {
        return lottoTickets + "";
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets);
    }
}
