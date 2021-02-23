package lotto.domain;

import lotto.exception.LottoCustomException;

import java.util.Set;

public class WinningTicket extends LottoTicket {

    private final LottoNumber bonusNumber;

    public WinningTicket(Set<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        super(lottoNumbers);
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (this.lottoNumbers.contains(bonusNumber)) {
            throw new LottoCustomException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public WinningResult getLottoResult(LottoTicket lottoTicket) {
        int matchCount = matchNumbers(lottoTicket);
        boolean isBonus = lottoTicket.contains(bonusNumber);
        return WinningResult.findWinningResult(matchCount, isBonus);
    }

    private int matchNumbers(LottoTicket ticket) {
        int count = (int) this.lottoNumbers.stream()
                .filter(ticket::contains)
                .count();
        return count;
    }
}
