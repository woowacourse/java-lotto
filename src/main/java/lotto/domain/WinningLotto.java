package lotto.domain;

import lotto.exception.LottoCustomException;

public class WinningLotto {

    public static final String DUPLICATE_NUMBERS_BY_BONUS_BALL_ERROR_MESSAGE = "보너스 볼은 지난 주 당첨번호와 중복될 수 없습니다.";

    private final LottoTicket winningTicket;
    private final LottoNumber bonusBall;

    public WinningLotto(LottoTicket winningTicket, LottoNumber bonusBall) {
        this.winningTicket = winningTicket;
        validateDuplicate(bonusBall);
        this.bonusBall = bonusBall;
    }

    private void validateDuplicate(LottoNumber bonusBall) {
        if (winningTicket.hasNumber(bonusBall)) {
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
