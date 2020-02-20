package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> sixNumbers, LottoNumber bonusNumber) {
        this.winningLottoTicket = new LottoTicket(sixNumbers);
        this.bonusNumber = bonusNumber;
    }

    public List<Rank> compareLottos(List<LottoTicket> lottoTickets) {
        List<Rank> ranks = new ArrayList<>();

        for (LottoTicket lottoTicket : lottoTickets) {
            compareLotto(ranks, lottoTicket);
        }
        return ranks;
    }

    private void compareLotto(List<Rank> ranks, LottoTicket lottoTicket) {
        Rank rank = lottoTicket.compare(winningLottoTicket, bonusNumber);
        if (rank != null) {
            ranks.add(rank);
        }
    }
}
