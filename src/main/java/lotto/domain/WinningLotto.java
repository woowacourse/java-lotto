package lotto.domain;

import lotto.exception.LottoCustomException;

public class WinningLotto {

    private static final String DUPLICATE_NUMBERS_BY_BONUS_BALL_ERROR_MESSAGE = "보너스 볼은 지난 주 당첨번호와 중복될 수 없습니다.";

    private final LottoTicket winningTicket;
    private final LottoNumber bonusBall;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusBall) {
        validateDuplicate(winningTicket, bonusBall);
        this.winningTicket = winningTicket;
        this.bonusBall = bonusBall;
    }

    private void validateDuplicate(LottoTicket ticket, LottoNumber bonus) {
        if(ticket.hasNumber(bonus)){
            throw new LottoCustomException(DUPLICATE_NUMBERS_BY_BONUS_BALL_ERROR_MESSAGE);
        }
    }

    public boolean hasBonus(LottoTicket lottoTicket) {
        return lottoTicket.hasNumber(bonusBall);
    }

    public LottoTicket getNumbers() {
        return winningTicket;
    }
}
