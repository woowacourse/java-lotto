package lotto.domain;

import static lotto.domain.LottoTicket.LOTTO_NUMBER_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호가 서로 중복되었습니다.";
    private static final String ERROR_NOT_MATCH_NUMBER_COUNT = "당첨 번호는 6개를 입력해주세요.";
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "지난주 당첨 번호와 중복되는 숫자입니다.";

    private final Set<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = new HashSet<>(winningNumbers);
        this.bonusNumber = bonusNumber;

        validateWinningNumbersDuplication(winningNumbers.size());
        validateWinningNumbersCount();
        validateBonusNumberDuplication();
    }

    private void validateBonusNumberDuplication() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new RuntimeException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    private void validateWinningNumbersDuplication(int inputNumbersSize) {
        if (this.winningNumbers.size() != inputNumbersSize) {
            throw new RuntimeException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    private void validateWinningNumbersCount() {
        if (winningNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new RuntimeException(ERROR_NOT_MATCH_NUMBER_COUNT);
        }
    }

    public Set<LottoNumber> getWinningNumbers() {
        return new HashSet<>(winningNumbers);
    }

    public LottoNumber getBonusNumber() {
        return LottoNumber.valueOf(bonusNumber.getNumber());
    }
}
