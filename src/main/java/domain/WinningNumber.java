package domain;

import exception.LottoException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {

  private static final String DUPLICATE_LOTTO_NUMBERS = "당첨번호는 중복될 수 없습니다!";
  private static final String INVALID_WINNING_NUMBER = "유효하지 않은 당첨 번호입니다.";
  private static final int LOTTO_LENGTH = 6;

  private final List<LottoNumber> lottoNumbers;

  public WinningNumber(List<Integer> inputWinningNumber) {
    validateWinningNumber(inputWinningNumber);
    lottoNumbers = inputWinningNumber.stream()
        .map(LottoNumber::new).toList();
  }

  public boolean isContain(LottoNumber lottoNumber) {
    return lottoNumbers.stream()
        .anyMatch(lottoNumber::equals);
  }

  private void validateWinningNumber(List<Integer> inputWinningNumber) {
    validateIsEmpty(inputWinningNumber);
    validateSizeCheck(inputWinningNumber);
    validateDuplication(inputWinningNumber);
  }

  private void validateDuplication(List<Integer> lottoNumbers) {
    Set<Integer> duplicationSet = new HashSet<>(lottoNumbers);
    if (lottoNumbers.size() != duplicationSet.size()) {
      throw new LottoException(DUPLICATE_LOTTO_NUMBERS);
    }
  }

  private void validateSizeCheck(List<Integer> winningNumbers) {
    if (winningNumbers.size() != LOTTO_LENGTH) {
      throw new LottoException(INVALID_WINNING_NUMBER);
    }
  }

  private void validateIsEmpty(List<Integer> lottoNumbers) {
    if (lottoNumbers == null) {
      throw new LottoException(INVALID_WINNING_NUMBER);
    }
  }

}
