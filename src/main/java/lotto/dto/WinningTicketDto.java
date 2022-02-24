package lotto.dto;

import java.util.List;

import lotto.exception.LottoException;
import lotto.exception.ticket.TicketNumbersExceptionStatus;

public class WinningTicketDto {

    private final TicketDto ticketDto;
    private final int bonusNumber;

    public WinningTicketDto(final List<Integer> winningNumbers, final int bonusNumber) {
        validateBonusNumberDuplicated(winningNumbers, bonusNumber);
        this.ticketDto = new TicketDto(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberDuplicated(final List<Integer> winningNumber, final int bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new LottoException(TicketNumbersExceptionStatus.NUMBERS_DUPLICATED);
        }
    }

    public List<Integer> getWinningNumbers() {
        return ticketDto.getBallNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

}
