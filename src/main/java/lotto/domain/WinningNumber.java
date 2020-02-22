package lotto.domain;

import lotto.exceptions.LottoNumberDuplicatedException;

import java.util.List;

public class WinningNumber {
    private static final String LOTTO_NUMBER_DUPLICATED_MESSAGE = "보너스 넘버와 중복될수 없습니다.";

    private Lotto winningNumber;
    private BonusNumber bonusNumber;

    public WinningNumber(Lotto winningNumber, BonusNumber bonusNumber) {
        this.winningNumber = winningNumber;

        if (bonusNumberDuplicatedWithWinningNumber(bonusNumber.getBonusNumber())) {
            throw new LottoNumberDuplicatedException(LOTTO_NUMBER_DUPLICATED_MESSAGE);
        }
        this.bonusNumber = bonusNumber;
    }

    private boolean bonusNumberDuplicatedWithWinningNumber(int bonusNumber) {
        return winningNumber.hasBonusNumber(bonusNumber);
    }

    public List<Integer> getWinningNumber() {
        return winningNumber.getNumbers();
    }

    public int getBonusNumber() {
        return bonusNumber.getBonusNumber();
    }
}
