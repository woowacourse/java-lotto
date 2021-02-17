package lotto.view;

import java.util.Scanner;

public class InputView {
  private static final Scanner SCAN = new Scanner(System.in);
  private static final String MONEY_MESSAGE = "구입금액을 입력해 주세요.";

  // 구입금액을 입력해 주세요. o
  // ~개를 구매했습니다.  OutputView o
  // 지난 주 당첨 번호를 입력해주세요.
  // 보너스 볼을 입력해주세요.
  // 당첨 통계  OutputView

  public static int money() {
    try{
      OutputView.printMessage(MONEY_MESSAGE);
      int money = Integer.parseInt(SCAN.nextLine());
      return money;
    } catch(NumberFormatException e){
      OutputView.printMessage("숫자를 입력해주세요.");
      return money();
    } catch(RuntimeException e) {
      return money();
    }
  }
}
