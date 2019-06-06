package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.domain.Result;

import java.util.*;

public class LottoTickets {
    private static final int MIN_MATCH_SCORE = 0;

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

        initializeMatchScore(lottoScore);
        calculateMatchScore(winningLotto, lottoScore);

        return lottoScore;
    }

    private void initializeMatchScore(Map<Rank, Integer> lottoScore) {
        for (Rank value : Rank.values()) {
            lottoScore.put(value, MIN_MATCH_SCORE);
        }
    }

    private void calculateMatchScore(WinningLotto winningLotto, Map<Rank, Integer> lottoScore) {
        for (Lotto ticket : lottoTickets) {
            Rank rank = winningLotto.match(ticket);
            lottoScore.put(rank, lottoScore.get(rank) + 1);
        }
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
