package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.exception.LottoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCAN = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "숫자를 입력해주세요.";

    public static Money getMoney() {
        try {
            OutputView.printMessage(INPUT_MONEY_MESSAGE);
            int money = Integer.parseInt(SCAN.nextLine());
            return Money.of(money);
        } catch (NumberFormatException e) {
            OutputView.printMessage(NUMBER_FORMAT_ERROR_MESSAGE);
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
            OutputView.printMessage(NUMBER_FORMAT_ERROR_MESSAGE);
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

    public static int getManualLottoCount() {
        try {
            OutputView.printMessage(INPUT_MANUAL_COUNT_MESSAGE);
            int count = Integer.parseInt(SCAN.nextLine());
            if (count < 0) {
                throw new IllegalArgumentException("숫자는 음수가 될 수 없습니다.");
            }
            return count;
        } catch (NumberFormatException e) {
            OutputView.printMessage(NUMBER_FORMAT_ERROR_MESSAGE);
            return getManualLottoCount();
        } catch (IllegalArgumentException e){
            OutputView.printMessage(e.getMessage());
            return getManualLottoCount();
        }
    }

    public static List<String> getManualNumbers(int count) {
        OutputView.printMessage(INPUT_MANUAL_NUMBER_MESSAGE);
        List<String> inputNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            inputNumbers.add(SCAN.nextLine());
        }
        return inputNumbers;
    }
}
