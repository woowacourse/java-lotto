package model.lottonumber;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.vo.LottoNumber;

public class WinningNumbers {
    private static final int LOTTO_NUMBER_SIZE_COUNT = 6;
    private static final String NOT_CORRECT_LOTTO_NUMBER_COUNT_ERROR_MESSAGE = "[ERROR] 입력한 당첨 번호가 6개가 아닙니다.";
    private static final String DUPLICATE_IN_WINNING_NUMBER_ERROR_MESSAGE = "[ERROR] 입력한 당첨번호들 중 중복되는 번호가 있습니다.";
    private static final String DUPLICATE_WITH_WINNING_NUMBER_ERROR_MESSAGE = "[ERROR] 입력한 보너스볼 번호가 당첨 번호와 중복됩니다.";

    private final List<LottoNumber> winningLottoNumbers;
    private final LottoNumber bonusLottoNumber;

    public WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        checkValidNumbers(winningNumbers, bonusNumber);
        this.winningLottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        this.bonusLottoNumber = new LottoNumber(bonusNumber);
    }

    private void checkValidNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        checkCountOfWinningNumbers(winningNumbers);
        checkDuplicateInNumbers(winningNumbers, bonusNumber);
    }

    private void checkCountOfWinningNumbers(final List<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_NUMBER_SIZE_COUNT) {
            throw new IllegalArgumentException(NOT_CORRECT_LOTTO_NUMBER_COUNT_ERROR_MESSAGE);
        }
    }

    private void checkDuplicateInNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        if (isDuplicateInWinningNumbers(winningNumbers)) {
            throw new IllegalArgumentException(DUPLICATE_IN_WINNING_NUMBER_ERROR_MESSAGE);
        }
        if (isDuplicateWithBonusNumber(winningNumbers, bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_WITH_WINNING_NUMBER_ERROR_MESSAGE);
        }
    }

    private boolean isDuplicateInWinningNumbers(final List<Integer> winningNumbers) {
        return winningNumbers.stream().distinct().count() != LOTTO_NUMBER_SIZE_COUNT;
    }

    private boolean isDuplicateWithBonusNumber(final List<Integer> winningNumbers, final int bonusNumber) {
        return winningNumbers.stream().anyMatch(winningNumber -> winningNumber == bonusNumber);
    }

    public List<LottoNumber> getWinningNumbers() {
        return winningLottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusLottoNumber;
    }
}
