package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.exception.LottoException;
import lotto.util.LottoGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCAN = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    public static Money getMoney() {
        try {
            OutputView.printMessage(INPUT_MONEY_MESSAGE);
            int money = Integer.parseInt(SCAN.nextLine());
            return Money.of(money);
        } catch (NumberFormatException e) {
            OutputView.printMessage("숫자를 입력해주세요.");
            return getMoney();
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return getMoney();
        }
    }

    public static List<Integer> getWinningNumbers() {
        try {
            OutputView.printMessage(INPUT_WINNING_NUMBER_MESSAGE);
            String winningNumbers = SCAN.nextLine();
            List<Integer> numbers = Arrays
                    .stream(winningNumbers.trim().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            return numbers;
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return getWinningNumbers();
        } catch (NumberFormatException e) {
            OutputView.printMessage("숫자를 입력해주세요.");
            return getWinningNumbers();
        }
    }

    public static LottoNumber getBonusNumber() {
        try {
            OutputView.printMessage(INPUT_BONUS_NUMBER_MESSAGE);
            return LottoNumber.of(Integer.parseInt(SCAN.nextLine()));
        } catch (LottoException e) {
            OutputView.printMessage(e.getMessage());
            return getBonusNumber();
        }
    }
}
