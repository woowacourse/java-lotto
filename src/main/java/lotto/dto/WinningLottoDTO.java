package lotto.dto;

import lotto.domain.lotto.LottoNumberGroup;

public class WinningLottoDTO {
    private LottoNumberGroup winningNumbers;
    private int bonusNumber;

    public LottoNumberGroup getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(LottoNumberGroup winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }
}
