package lotto.dto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.winning.WinningTicket;

public class WinningTicketDto {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningTicketDto(final List<Integer> winningNumbers, final int bonusNumber) {
        this.winningNumbers = new ArrayList<>(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public WinningTicket toWinningTicket() {
        return new WinningTicket(winningNumbers, bonusNumber);
    }

}
