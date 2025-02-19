package view;

import static view.validator.LottoInputValidator.validateMoney;
import static view.validator.LottoInputValidator.validateNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

  private static final Scanner scanner = new Scanner(System.in);

  public static int inputBuyLottoMoney() {
    OutputView.printBuyLottoMoney();
    String inputMoney = input();
    validateMoney(inputMoney);
    return Integer.parseInt(inputMoney);
  }

  public static List<Integer> inputWinningNumber() {
    OutputView.printInputWinningNumber();
    String inputWinningNumber = input();
    String[] winningNumbers = inputWinningNumber.split(",", -1);
    for (String winningNumber : winningNumbers) {
      validateNumber(winningNumber);
    }
    return Arrays.stream(winningNumbers)
        .map(((winningNumber) -> Integer.parseInt(winningNumber.strip())))
        .toList();
  }

  public static int inputBonusNumber() {
    OutputView.printInputBonusNumber();
    String inputNumber = input().strip();
    validateNumber(inputNumber);
    return Integer.parseInt(inputNumber);
  }

  private static String input() {
    return scanner.nextLine();
  }

}
