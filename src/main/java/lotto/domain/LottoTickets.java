package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets = lottoTickets;
    }

    public static LottoTickets create(LottoNumberStrategy lottoNumberStrategy) {
        return new LottoTickets(createLottoTickets(lottoNumberStrategy));
    }

    private static List<LottoTicket> createLottoTickets(LottoNumberStrategy lottoNumberStrategy) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        List<List<LottoNumber>> lottoNumbers = lottoNumberStrategy.getLottoNumbers();
        for (List<LottoNumber> lottoNumber : lottoNumbers) {
            lottoTickets.add(new LottoTicket(lottoNumber));
        }
        return lottoTickets;
    }

    public List<LottoTicket> getLottoTickets() {
        return new ArrayList<>(lottoTickets);
    }

    public List<Rank> getRanksWithWinningNumbers(WinningNumbers winningNumbers) {
        List<Rank> ranks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets) {
            ranks.add(lottoTicket.compareNumbers(winningNumbers.getWinningNumbers(), winningNumbers.getBonusNumber()));
        }
        return ranks;
    }

    public LottoTickets combine(LottoTickets lottoTickets) {
        this.lottoTickets.addAll(lottoTickets.getLottoTickets());
        return new LottoTickets(this.lottoTickets);
    }
}
