package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String WRONG_NUMBER_EXCEPTION_MESSAGE = "자연수만 입력 가능합니다. 현재 입력 값 : %s.";
    private static final String BETTING_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_LOTTO_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_TICKET_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Set<Integer> inputWinningNumbers() {
        System.out.println(WINNING_LOTTO_MESSAGE);
        String winningNumbersText = scanner.nextLine();
        return Arrays.stream(winningNumbersText.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public List<Integer> inputManualTicketNumber() {
        String manualNumber = scanner.nextLine();
        return Arrays.stream(manualNumber.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int inputManualTicketCount() {
        System.out.println(MANUAL_TICKET_COUNT);
        return nextInt();
    }

    public int inputBonusNumber() {
        System.out.println(BONUS_LOTTO_MESSAGE);
        return nextInt();
    }

    public int inputBettingMoney() {
        System.out.println(BETTING_MONEY_MESSAGE);
        return nextInt();
    }

    private int nextInt() {
        String inputValue = scanner.nextLine();
        try {
            return Integer.parseInt(inputValue);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(String.format(WRONG_NUMBER_EXCEPTION_MESSAGE, inputValue));
        }
    }
}
