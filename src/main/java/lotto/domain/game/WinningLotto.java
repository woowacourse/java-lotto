package lotto.domain.game;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;

import java.util.List;

public class WinningLotto {
    private final LottoTicket lottoTicket;

    private final LottoNumber bonusNumber;

    private WinningLotto(final List<LottoNumber> lottoNumbers, final LottoNumber bonusNumber) {
        this.lottoTicket = new LottoTicket(lottoNumbers);
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(final List<LottoNumber> winningNumbers, final LottoNumber bonusNumber) {
        validateBonusNumContain(winningNumbers, bonusNumber);
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private static void validateBonusNumContain(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버는 당첨 로또 번호 외의 숫자여야 합니다.");
        }
    }

    public Rank getRank(LottoTicket lottoTicket) {
        return Rank.valueOf(this.lottoTicket.countSameNumber(lottoTicket), lottoTicket.isContainsBonusNumber(bonusNumber));
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public LottoTicket getLottoTicket() {
        return lottoTicket;
    }
}
