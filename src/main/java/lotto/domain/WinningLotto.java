package lotto.domain;

import lotto.domain.Factory.LottoTicketFactory;
import lotto.exception.DuplicatedInputException;
import lotto.exception.ExceptionMessage;

public class WinningLotto {
    private static WinningLotto WINNING_LOTTO = null;

    private final LottoTicket lottoNumbers;
    private final LottoNumber bonusBall;

    private WinningLotto(final String winningLotto, final int bonusBall) {
        this.lottoNumbers = LottoTicketFactory.getInstance().create(winningLotto);
        this.bonusBall = LottoNumber.getInstance(bonusBall);

        validateDistinctNumber(bonusBall);
    }

    public static WinningLotto of(final String winningLotto, final int bonusBall) {
        if (WINNING_LOTTO == null) {
            WINNING_LOTTO = new WinningLotto(winningLotto, bonusBall);
        }

        return WINNING_LOTTO;
    }

    public int getMatchingCount(LottoTicket lottoTicket) {
        return lottoNumbers.getMatchingCount(lottoTicket);
    }

    public boolean matchesBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.hasSameNumber(this.bonusBall);
    }


    private void validateDistinctNumber(int lottoNumber) {
        boolean isDistinct = this.lottoNumbers.hasSameNumber(LottoNumber.getInstance(lottoNumber));

        if (isDistinct) {
            throw new DuplicatedInputException(ExceptionMessage.DUPLICATED_NUMBER_EXCEPTION);
        }
    }

    static void makeObjectNull() {
        WINNING_LOTTO = null;
    }
}
