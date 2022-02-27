package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lotto.domain.lottoticket.LottoTicket;

public class LottoTickets {
    private final List<LottoTicket> value = new ArrayList<>();

    private LottoTickets(int count) {
        generateTickets(count);
    }

    private void generateTickets(int count) {
        for (int i = 0; i < count; i++) {
            value.add(LottoTicket.generateRandom());
        }
    }

    public static LottoTickets generateRandomByCount(int count) {
        return new LottoTickets(count);
    }

    public WinningResult calculateWinningStatistic(WinningNumbers winningNumbers) {
        List<Ranking> rankings = value.stream()
                .map(winningNumbers::calculateRanking)
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
        return new WinningResult(rankings);
    }

    public List<LottoTicket> getValue() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTickets that = (LottoTickets) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LottoTickets{" +
                "lottoTickets=" + value +
                '}';
    }
}
