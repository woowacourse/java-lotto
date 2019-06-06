package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.domain.Result;

import java.util.*;

public class LottoTickets {
    private final List<Lotto> lottoTickets;

    public LottoTickets(List<Lotto> lottos) {
        if (Objects.isNull(lottos) || lottos.isEmpty()) {
            throw new NullPointerException();
        }
        this.lottoTickets = lottos;
    }

    public Result match(WinningLotto winningLotto) {
        if (Objects.isNull(winningLotto)) {
            throw new NullPointerException();
        }

        return new Result(createMatchScore(winningLotto));
    }

    private Map<Rank, Integer> createMatchScore(WinningLotto winningLotto) {
        Map<Rank, Integer> lottoScore = new HashMap<>();

        for (Lotto ticket : lottoTickets) {
            calculateCountOfMatch(winningLotto, lottoScore, ticket);
        }

        return lottoScore;
    }

    private void calculateCountOfMatch(WinningLotto winningLotto, Map<Rank, Integer> lottoScore, Lotto ticket) {
        Rank rank = winningLotto.match(ticket);

        if (lottoScore.containsKey(rank)) {
            lottoScore.put(rank, lottoScore.get(rank) + 1);
            return;
        }

        lottoScore.put(rank, 1);
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
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
