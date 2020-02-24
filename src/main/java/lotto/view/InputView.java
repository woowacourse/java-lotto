package lotto.view;

import lotto.exception.ConvertFailException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String CONVERT_FAIL_EXCEPTION_MESSAGE = "%s : 잘못된 숫자 입력";
    private static final String EMPTY_EXCEPTION_MESSAGE = "입력값이 비어있습니다.";

    private static final String COMMA = ",";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputBettingMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String bettingMoney = scanner.nextLine();
        validateEmpty(bettingMoney);

        return convertToInteger(bettingMoney);
    }


    public Set<Integer> inputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String winningNumber = scanner.nextLine();
        validateEmpty(winningNumber);

        return Arrays.stream(winningNumber.split(COMMA))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String bonusNumber = scanner.nextLine();
        validateEmpty(bonusNumber);

        return convertToInteger(bonusNumber);
    }

    private void validateEmpty(String input) {
        if (isEmpty(input)) {
            throw new IllegalArgumentException(EMPTY_EXCEPTION_MESSAGE);
        }
    }

    private boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    private int convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ConvertFailException(String.format(CONVERT_FAIL_EXCEPTION_MESSAGE, input));
        }
    }

    public int inputManualTicketAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String manualLottoAmount = scanner.nextLine();
        validateEmpty(manualLottoAmount);

        return convertToInteger(manualLottoAmount);
    }

    public List<String> inputManualNumbers(int manualAmount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> numbers = new ArrayList<>();
        for (int count = 0; count < manualAmount; count++) {
            numbers.add(scanner.nextLine());
        }
        return numbers;
    }

}
