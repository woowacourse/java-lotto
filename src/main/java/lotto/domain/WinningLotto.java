package lotto.domain;

import lotto.exception.IllegalWinningLottoException;

public class WinningLotto {
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.winningTicket = new LottoTicket(winningTicket.lottoTicket());
        this.bonusNumber = new LottoNumber(bonusNumber.toString());
        validateWinningLotto();
    }

    private void validateWinningLotto() {
        if (isContainBonusNumber(winningTicket)) {
            throw new IllegalWinningLottoException();
        }
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
