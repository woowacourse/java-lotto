package domain;

import java.util.List;

public class Lotto {

  private final List<Integer> numbers;

  public Lotto(List<Integer> numbers) {
    this.numbers = numbers;
  }

  public LottoMatch compareLotto(WinningLotto winningLotto) {
    List<Integer> winningNumbers = winningLotto.getWinningNumbers();
    int bonusNumber = winningLotto.getBonusNumber();
    int winningCounter = 0;
    boolean bonusChecker = false;

    for (int number : numbers) {
      winningCounter += checkWinningCounter(winningNumbers, number);
    }
    if (numbers.contains(bonusNumber)) {
      bonusChecker = true;
    }
    return LottoMatch.findLottoRank(winningCounter, bonusChecker);
  }

  private int checkWinningCounter(List<Integer> winningNumbers, int number) {
    if (winningNumbers.contains(number)) {
      return 1;
    }
    return 0;
  }

  @Override
  public String toString() {
    return numbers.toString();
  }
}
