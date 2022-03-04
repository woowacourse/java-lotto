package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lottonumbergenerator.LottoNumberGenerator;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    public LottoTickets(LottoNumberGenerator lottoNumberGenerator, int lottoCount) {
        this.lottoTickets = createLottoTickets(lottoNumberGenerator, lottoCount);
    }

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public LottoTickets combine(LottoTickets lottoTickets) {
        List<LottoTicket> combined = new ArrayList<>(this.lottoTickets);
        combined.addAll(lottoTickets.getLottoTickets());
        return new LottoTickets(combined);
    }

    public List<Rank> getRanksWithWinningNumbers(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.add(lottoTicket.getRankBy(winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber()));
        }
        return ranks;
    }

    private List<LottoTicket> createLottoTickets(LottoNumberGenerator lottoNumberGenerator, int lottoCount) {
        return lottoNumberGenerator.getLottoNumbersBy(lottoCount).stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }
}