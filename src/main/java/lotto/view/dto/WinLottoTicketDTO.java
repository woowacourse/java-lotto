package lotto.view.dto;

import java.util.List;

public class WinLottoTicketDTO {
    private final List<Integer> winNumbers;
    private final int bonusNumber;

    public WinLottoTicketDTO(List<Integer> winNumbers, int bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
