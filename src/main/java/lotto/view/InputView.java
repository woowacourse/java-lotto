package lotto.view;

import java.util.Arrays;
import java.util.Scanner;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.util.LottoGenerator;

public class InputView {

  private static final Scanner SCAN = new Scanner(System.in);
  private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
  private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
  private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

  // 구입금액을 입력해 주세요. o
  // ~개를 구매했습니다.  OutputView o
  // 지난 주 당첨 번호를 입력해주세요.
  // 보너스 볼을 입력해주세요.
  // 당첨 통계  OutputView

  public static int money() {
    try {
      OutputView.printMessage(INPUT_MONEY_MESSAGE);
      int money = Integer.parseInt(SCAN.nextLine());
      return money;
    } catch (NumberFormatException e) {
      OutputView.printMessage("숫자를 입력해주세요.");
      return money();
    } catch (RuntimeException e) {
      return money();
    }
  }

  public static WinningLotto winningLotto() {
    try {
      return new WinningLotto(winningNumbers(), bonusNumber());
    } catch (RuntimeException e) {
      System.out.println("예외가 발생했습니다!");
      return winningLotto();
    }
  }

  private static Lotto winningNumbers() {
    try {
      OutputView.printMessage(INPUT_WINNING_NUMBER_MESSAGE);
      String winningNumbers = SCAN.nextLine();
      LottoGenerator lottoGenerator = new LottoGenerator();
      int[] numbers = Arrays
          .stream(winningNumbers.trim().split(","))
          .mapToInt(Integer::parseInt)
          .toArray();
      return lottoGenerator.generate(numbers);
    } catch (RuntimeException e) {
      System.out.println("예외가 발생했습니다!");
      return winningNumbers();
    }
  }

  private static LottoNumber bonusNumber() {
    try {
      OutputView.printMessage(INPUT_BONUS_NUMBER_MESSAGE);
      return LottoNumber.of(Integer.parseInt(SCAN.nextLine()));
    } catch (RuntimeException e) {
      System.out.println("예외가 발생했습니다!");
      return bonusNumber();
    }
  }
}
