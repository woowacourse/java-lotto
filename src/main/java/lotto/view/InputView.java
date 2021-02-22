package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import lotto.domain.LottoGroup;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.exception.LottoException;
import lotto.exception.LottoPriceException;
import lotto.util.LottoGenerator;
import lotto.util.LottoSeller;
import lotto.util.RandomLottoStrategy;

public class InputView {

  private static final Scanner SCAN = new Scanner(System.in);
  private static final String INPUT_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해주세요.";
  private static final String INPUT_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해주세요";
  private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
  private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
  private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";


  public static LottoGroup lottoGroup() {
    try {
      Money money = money();
      LottoGroup lottoGroup = new LottoGroup();
      Money change = inputLottoGroup(lottoGroup, money);
      randomLottoGroup(lottoGroup, change);
      return lottoGroup;
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return lottoGroup();
    }
  }

  private static Money inputLottoGroup(LottoGroup lottoGroup, Money money) {
    int count = inputCount(money);
    lottoGroup.setInputLottoCount(count);
    OutputView.printMessage(INPUT_LOTTO_NUMBER);
    for (int i = 0; i < count; i++) {
      money = LottoSeller.sellLotto(money, lottoGroup, InputView::lottoNumbers);
    }
    return money;
  }

  private static int inputCount(Money money) {
    try {
      OutputView.printMessage(INPUT_LOTTO_COUNT);
      int count = Integer.parseInt(SCAN.nextLine());
      int affordableCount = money.affordableCount(LottoSeller.lottoPrice());
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

  private static void randomLottoGroup(LottoGroup lottoGroup, Money money) {
    int randomLottoCount = money.affordableCount(LottoSeller.lottoPrice());
    lottoGroup.setRandomLottoCount(randomLottoCount);
    for (int i = 0; i < randomLottoCount; i++) {
      LottoSeller.sellLotto(money, lottoGroup, new RandomLottoStrategy());
    }
  }

  private static Money money() {
    try {
      OutputView.printMessage(INPUT_MONEY_MESSAGE);
      int money = Integer.parseInt(SCAN.nextLine());
      return Money.of(money);
    } catch (NumberFormatException e) {
      OutputView.printMessage("숫자를 입력해주세요.");
      return money();
    } catch (LottoException e) {
      OutputView.printMessage(e.getMessage());
      return money();
    }
  }

  public static WinningLotto winningLotto() {
    try {
      OutputView.printMessage(INPUT_WINNING_NUMBER_MESSAGE);
      return new WinningLotto(LottoGenerator.generate(InputView::lottoNumbers), bonusNumber());
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
