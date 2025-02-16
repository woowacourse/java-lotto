package model;

import java.util.List;
import java.util.Objects;

public class LottoRankFinder {

    public List<LottoRank> findAll(List<LottoTicket> lottoTickets, WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(lottoTicket -> find(lottoTicket, winningLotto))
                .filter(Objects::nonNull)
                .toList();
    }

    public LottoRank find(LottoTicket lottoTicket, WinningLotto winningLotto) {
        int overlappedCount = winningLotto.countOverlappedNumbers(lottoTicket.getNumbers());
        if (LottoRank.requiredBonusNumber(overlappedCount)) {
            boolean isBonusNumberOverlapped = winningLotto.isOverlappedBonusNumber(lottoTicket.getNumbers());
            return LottoRank.findByMatchCondition(overlappedCount, isBonusNumberOverlapped);
        }
        return LottoRank.findByMatchCondition(overlappedCount, false);
    }
}
