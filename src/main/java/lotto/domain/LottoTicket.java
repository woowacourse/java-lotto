package lotto.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTicket {
    private List<LottoNumbers> lottoTicket = new ArrayList<>();

    public LottoTicket(int count) {
        generateTickets(count);
    }

    public LottoTicket(final List<LottoNumbers> lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    private void generateTickets(int count) {
        for (int i = 0; i < count; i++) {
            lottoTicket.add(new LottoNumbers());
        }
    }

    public List<LottoNumbers> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public WinningResult calculateWinningStatistic(WinningNumbers winningNumbers) {
        List<Ranking> rankings = lottoTicket.stream()
                .map(winningNumbers::calculateRanking)
                .filter(Objects::nonNull)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
        return new WinningResult(rankings);
    }

    public void addLottoTicket(LottoTicket otherLottoTicket) {
        lottoTicket.addAll(otherLottoTicket.getLottoTicket());
    }
}
