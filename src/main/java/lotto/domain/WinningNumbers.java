package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {

    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호가 서로 중복되었습니다.";
    private static final String ERROR_NOT_MATCH_NUMBER_COUNT = "당첨 번호는 6개를 입력해주세요.";
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "지난주 당첨 번호와 중복되는 숫자입니다.";
    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) throws RuntimeException {
        validateWinningNumbersCount(winningNumbers);
        validateWinningNumbersDuplication(winningNumbers);
        validateBonusNumberDuplication(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningNumbersCount(List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new RuntimeException(ERROR_NOT_MATCH_NUMBER_COUNT);
        }
    }

    private void validateWinningNumbersDuplication(List<LottoNumber> winningNumbers) {
        if (winningNumbers.stream().distinct().count() != winningNumbers.size()) {
            throw new RuntimeException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    private void validateBonusNumberDuplication(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new RuntimeException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    public List<LottoNumber> getWinningNumbers() {
        return new ArrayList<>(winningNumbers);
    }

    public LottoNumber getBonusNumber() {
        return LottoNumber.valueOf(bonusNumber.getNumber());
    }
}
