package lotto.domain;

import lotto.exception.LottoCustomException;

public class WinningTicket {

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusNumber;

    public WinningTicket(LottoTicket lottoTicket, LottoNumber bonusNumber) {
        this.lottoTicket = lottoTicket;

        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (lottoTicket.contains(bonusNumber)) {
            throw new LottoCustomException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public Rank getLottoResult(LottoTicket lottoTicket) {
        int matchCount = matchNumbers(lottoTicket);
        boolean isBonus = lottoTicket.contains(bonusNumber);
        return Rank.of(matchCount, isBonus);
    }

    private int matchNumbers(LottoTicket ticket) {
        return (int) this.lottoTicket
                .getLottoNumbers()
                .stream()
                .filter(ticket::contains)
                .count();
    }
}
