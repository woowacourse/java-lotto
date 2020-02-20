package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {
    private final LottoTicket winningLottoTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        validateDuplication(winningLottoTicket, bonusNumber);
        this.winningLottoTicket = winningLottoTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplication(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
        if (winningLottoTicket.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
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
