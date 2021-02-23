package lotto.domain.result;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class WinningLotto {
    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (isContainBonusNumber(winningTicket)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호가 중복됩니다.");
        }
    }

    public LottoResult checkPrizes(LottoTickets lottoTickets) {
        return lottoTickets.lottoTickets().stream()
                .map(this::matchPrize)
                .collect(collectingAndThen(toList(), LottoResult::new));
    }

    public Prize matchPrize(LottoTicket lottoTicket) {
        int matchCount = getMatchingCount(lottoTicket);
        boolean isBonusNumber = isContainBonusNumber(lottoTicket);
        return Prize.findPrize(matchCount, isBonusNumber);
    }

    private int getMatchingCount(LottoTicket lottoTicket) {
        return (int) lottoTicket.lottoTicket().stream()
                .filter(winningTicket.lottoTicket()::contains)
                .count();
    }

    private boolean isContainBonusNumber(LottoTicket lottoTicket) {
        return lottoTicket.lottoTicket()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }
}
