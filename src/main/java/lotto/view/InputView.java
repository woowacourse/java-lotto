package lotto.view;

import lotto.exception.LottoCustomException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {

    private static final String SEPARATOR = ",";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new LottoCustomException("구입 금액은 숫자만 가능합니다.");
        }
    }

    public int inputMoney() {
        return parseInt(scanner.nextLine());
    }

    public Set<Integer> inputWinningTicket() {
        return splitWinningNumbers(scanner.nextLine());
    }

    public int inputBonusNumber() {
        return parseInt(scanner.nextLine());
    }

    private Set<Integer> splitWinningNumbers(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(SEPARATOR))
                .map(String::trim)
                .map(InputView::parseInt)
                .collect(Collectors.toSet());
    }
}
