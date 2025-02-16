package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final char MATCH_LOTTO_DELIMITER = ',';

    public final Scanner sc;

    public InputView(final Scanner sc) {
        this.sc = sc;
    }

    public int inputMoney() {
        final String moneyString = sc.nextLine();
        validateMoneyNotBlank(moneyString);
        validateNumberFormat(moneyString);
        return Integer.parseInt(moneyString);
    }

    private void validateMoneyNotBlank(final String moneyString) {
        if (moneyString.isBlank()) {
            throw new IllegalStateException("금액을 입력해야 합니다.");
        }
    }

    private void validateNumberFormat(final String moneyString) {
        try {
            Integer.parseInt(moneyString);
        } catch (NumberFormatException exception) {
            throw new IllegalStateException("금액은 숫자로만 입력해야 합니다.", exception);
        }
    }

    public List<Integer> inputMatchLotto() {
        final String numbersString = sc.nextLine();

        validateMatchLottoDelimiter(numbersString);

        final String[] numbers = numbersString.split(String.valueOf(MATCH_LOTTO_DELIMITER));

        return Arrays.stream(numbers)
            .map(String::trim)
            .map(Integer::parseInt)
            .toList();
    }

    private void validateMatchLottoDelimiter(final String input) {
        for (char ch : input.toCharArray()) {
            if (!Character.isDigit(ch) && ch != MATCH_LOTTO_DELIMITER) {
                throw new IllegalArgumentException("당첨 로또 번호는 쉼표(,)로 구분되어야 합니다.");
            }
        }
    }

    public int inputBonusNumber() {
        final String bonusString = sc.nextLine();
        return Integer.parseInt(bonusString);
    }
}
