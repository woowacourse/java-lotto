package lotto.domain;

import lotto.domain.factory.LottoTicketFactory;
import lotto.exception.DuplicatedInputException;
import lotto.exception.ExceptionMessage;

public class WinningLotto {
    private final LottoTicket winningNumbers;
    private final LottoNumber bonusBall;

    private WinningLotto(final String winningLotto, final int bonusBall) {
        this.winningNumbers = LottoTicketFactory.getInstance().create(winningLotto);
        this.bonusBall = LottoNumber.getInstance(bonusBall);

        validateDistinctNumber(bonusBall);
    }

    public static WinningLotto of(final String winningLotto, final int bonusBall) {
        return new WinningLotto(winningLotto, bonusBall);
    }

    public LottoTicket getWinningNumbers() {
        return winningNumbers;
    }

    public LottoNumber getBonusBall() {
        return bonusBall;
    }

    public int getMatchingCount(LottoTicket lottoTicket) {
        return winningNumbers.getMatchingCount(lottoTicket);
    }

    public boolean matchesBonusBall(LottoTicket lottoTicket) {
        return lottoTicket.hasSameNumber(this.bonusBall);
    }

    private void validateDistinctNumber(int lottoNumber) {
        boolean isDistinct = this.winningNumbers.hasSameNumber(LottoNumber.getInstance(lottoNumber));

        if (isDistinct) {
            throw new DuplicatedInputException(ExceptionMessage.DUPLICATED_NUMBER_EXCEPTION);
        }
    }
}
