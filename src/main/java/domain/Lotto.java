package domain;

import static util.constant.Values.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {

  private List<Integer> numbers = new ArrayList<>();

  public Lotto() {
    createNumbers();
  }

  private void createNumbers() {
    Random random = new Random();
    do {
      int randomNumber = random.nextInt(LOTTO_MIN_NUM, LOTTO_MAX_NUM + 1);
      addNumber(randomNumber);
    } while (numbers.size() < LOTTO_SIZE);
    Collections.sort(numbers);
  }

  private void addNumber(int randomNumber) {
    if (!numbers.contains(randomNumber)) {
      numbers.add(randomNumber);
    }
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
    return LottoMatch.calculateLotto(winningCounter, bonusChecker);
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
