package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final String NULL_WINNING_NUMBER_ERROR = "[ERROR] 로또 당첨번호에 null 값이 올 수 없습니다.";
    private static final String NULL_BONUS_NUMBER_ERROR = "[ERROR] 로또 보너스볼에 null 값이 올 수 없습니다.";
    private static final String EMPTY_WINNING_NUMBER_ERROR = "[ERROR] 로또 당첨번호에 빈 값이 올 수 없습니다.";
    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.";

    private static final int EMPTY = 0;


    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        checkValidate(winningNumbers, bonusNumber);
        this.winningNumbers = new ArrayList<>(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningNumbers.contains(lottoNumber);
    }

    public boolean isEqualToBonusNumber(LottoNumber lottoNumber) {
        return bonusNumber.equals(lottoNumber);
    }

    private void checkValidate(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        checkNull(winningNumbers, bonusNumber);
        checkWinningNumbersEmpty(winningNumbers);
        checkDuplicate(winningNumbers, bonusNumber);
    }

    private void checkNull(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        checkWinningNumbersNull(winningNumbers);
        checkBonusNumberNull(bonusNumber);
    }

    private void checkWinningNumbersNull(List<LottoNumber> winningNumbers) {
        if (winningNumbers == null) {
            throw new IllegalArgumentException(NULL_WINNING_NUMBER_ERROR);
        }
    }

    private void checkBonusNumberNull(LottoNumber bonusNumber) {
        if (bonusNumber == null) {
            throw new IllegalArgumentException(NULL_BONUS_NUMBER_ERROR);
        }
    }

    private void checkWinningNumbersEmpty(List<LottoNumber> winningNumbers) {
        if (winningNumbers.size() == EMPTY) {
            throw new IllegalArgumentException(EMPTY_WINNING_NUMBER_ERROR);
        }
    }

    private void checkDuplicate(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        List<LottoNumber> numbers = new ArrayList<>(winningNumbers);
        numbers.add(bonusNumber);
        Set<LottoNumber> noDuplicatedNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicatedNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }
    }


}
