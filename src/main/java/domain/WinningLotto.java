package domain;

import static util.constant.Message.*;
import static util.constant.Values.*;

import java.util.*;

public class WinningLotto {

  private List<Integer> winningNumbers;
  private int bonusNumber;

  public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
    validateSize(winningNumbers);
    validateNumberRange(winningNumbers, bonusNumber);
    validateDuplicate(winningNumbers, bonusNumber);

    this.winningNumbers = winningNumbers;
    this.bonusNumber = bonusNumber;
  }

  private void validateSize(List<Integer> winningNumbers) {
    if (winningNumbers.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException(WINNING_NUMBER_SIZE_ERROR);
    }
  }

  private void validateNumberRange(List<Integer> winningNumbers, int bonusNumber) {
    for (int number : winningNumbers) {
      checkNumberRange(number);
    }
    checkNumberRange(bonusNumber);
  }

  private void validateDuplicate(List<Integer> winningNumbers, int bonusNumber) {
    Set<Integer> set = new HashSet<>(winningNumbers);
    if (set.size() != LOTTO_SIZE) {
      throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE_ERROR);
    }

    if (winningNumbers.contains(bonusNumber)) {
      throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR);
    }
  }

  private void checkNumberRange(int number) {
    if (number < LOTTO_MIN_NUM || number > LOTTO_MAX_NUM) {
      throw new IllegalArgumentException(WINNING_NUMBER_RANGE_ERROR);
    }
  }

  public List<Integer> getWinningNumbers() {
    return new ArrayList<>(winningNumbers);
  }

  public int getBonusNumber() {
    return bonusNumber;
  }
}
