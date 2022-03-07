package lotto.domain;

import java.util.List;

public class WinningNumbers {

    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "지난주 당첨 번호와 중복되는 숫자입니다.";

    private final LottoTicket winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) throws RuntimeException {
        validateBonusNumberDuplication(winningNumbers, bonusNumber);

        this.winningNumbers = new LottoTicket(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberDuplication(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new RuntimeException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    public LottoTicket getWinningNumbers() {
        return new LottoTicket(winningNumbers.getNumbers());
    }

    public LottoNumber getBonusNumber() {
        return LottoNumber.valueOf(bonusNumber.getNumber());
    }
}
