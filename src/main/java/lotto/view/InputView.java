package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.lottoGroup.ManualLottoGroup;
import lotto.domain.lottoGroup.MixedLottoGroup;
import lotto.domain.lottoGroup.RandomLottoGroup;
import lotto.exception.LottoException;
import lotto.exception.LottoPriceException;

public class InputView {

  private static final Scanner SCAN = new Scanner(System.in);
  private static final String INPUT_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해주세요.";
  private static final String INPUT_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해주세요";
  private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
  private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
  private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";


  public static MixedLottoGroup lottoGroup() {
    try {
      Money money = money();
      ManualLottoGroup manual = manualLottoGroup(money);
      Money change = money.minus(manual.size() * Lotto.price());
      return new MixedLottoGroup(manual, randomLottoGroup(change));
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return lottoGroup();
    }
  }

  private static ManualLottoGroup manualLottoGroup(Money money) {
    int count = inputCount(money);
    OutputView.printMessage(INPUT_LOTTO_NUMBER);
    List<String> lottoNumbers = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      lottoNumbers.add(SCAN.nextLine());
    }
    return new ManualLottoGroup(lottoNumbers);
  }

  private static int inputCount(Money money) {
    try {
      OutputView.printMessage(INPUT_LOTTO_COUNT);
      int count = Integer.parseInt(SCAN.nextLine());
      int affordableCount = money.affordableCount(Lotto.price());
      if (affordableCount < count) {
        throw new LottoPriceException("금액이 부족합니다.");
      }
      return count;
    } catch (NumberFormatException e) {
      OutputView.printMessage("숫자를 입력해주세요.");
      return inputCount(money);
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return inputCount(money);
    }
  }

  private static RandomLottoGroup randomLottoGroup(Money money) {
    int randomLottoCount = money.affordableCount(Lotto.price());
    return new RandomLottoGroup(randomLottoCount);
  }

  private static Money money() {
    try {
      OutputView.printMessage(INPUT_MONEY_MESSAGE);
      int money = Integer.parseInt(SCAN.nextLine());
      validateMoney(money);
      return Money.of(money);
    } catch (NumberFormatException e) {
      OutputView.printMessage("숫자를 입력해주세요.");
      return money();
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return money();
    }
  }

  private static void validateMoney(int money) {
    if (money < Lotto.price()) {
      throw new LottoPriceException("최소 " + Lotto.price() + "원 이상을 입력해주세요.");
    }
  }

  public static WinningLotto winningLotto() {
    try {
      OutputView.printMessage(INPUT_WINNING_NUMBER_MESSAGE);
      return new WinningLotto(new Lotto(lottoNumbers()), bonusNumber());
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return winningLotto();
    }
  }

  private static List<LottoNumber> lottoNumbers() {
    try {
      String winningNumbers = SCAN.nextLine();

      int[] numbers = Arrays
          .stream(winningNumbers.trim().split(","))
          .mapToInt(Integer::parseInt)
          .toArray();

      return LottoNumber.asList(numbers);
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return lottoNumbers();
    } catch (NumberFormatException e) {
      OutputView.printMessage("숫자를 입력해주세요.");
      return lottoNumbers();
    }
  }

  private static LottoNumber bonusNumber() {
    try {
      OutputView.printMessage(INPUT_BONUS_NUMBER_MESSAGE);
      return LottoNumber.of(Integer.parseInt(SCAN.nextLine()));
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return bonusNumber();
    }
  }
}
