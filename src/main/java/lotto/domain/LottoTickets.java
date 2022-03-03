package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lottoticket.LottoTicket;

public class LottoTickets {
    private final List<LottoTicket> value;

    private LottoTickets(List<LottoTicket> manualLottoTickets, int count) {
        value = generateTickets(manualLottoTickets, count);
    }

    private List<LottoTicket> generateTickets(List<LottoTicket> manualLottoTickets, int count) {
        for (int i = 0; i < count; i++) {
            manualLottoTickets.add(LottoTicket.generateRandom());
        }
        return Collections.unmodifiableList(manualLottoTickets);
    }

    public static LottoTickets generateRandomWithManualTickets(List<LottoTicket> manualLottoTickets, int count) {
        return new LottoTickets(manualLottoTickets, count);
    }

    public WinningResult calculateWinningStatistic(WinningNumbers winningNumbers) {
        List<Ranking> rankings = value.stream()
                .map(winningNumbers::calculateRanking)
                .collect(Collectors.toList());
        return new WinningResult(rankings);
    }

    public List<LottoTicket> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LottoTickets{" +
                "lottoTickets=" + value +
                '}';
    }
}
