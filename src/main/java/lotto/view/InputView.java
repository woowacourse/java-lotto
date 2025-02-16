package lotto.view;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    public static final String COMMA = ",";

    public int requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return validateAndParsePositiveInteger(Console.readLine());
    }

    public List<Integer> requestWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return parseWinningNumbers(Console.readLine());
    }

    private List<Integer> parseWinningNumbers(String input) {
        String[] splitValues = input.split(COMMA);
        return parsePositiveIntegers(splitValues);
    }

    private List<Integer> parsePositiveIntegers(String[] values) {
        List<Integer> numbers = new ArrayList<>();
        for (String token : values) {
            numbers.add(validateAndParsePositiveInteger(token));
        }
        return List.copyOf(numbers);
    }

    public int requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return validateAndParsePositiveInteger(Console.readLine());
    }

    private int validateAndParsePositiveInteger(String value) {
        int number = parseInt(value);
        validatePositive(parseInt(value));
        return number;
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수일 수 없습니다.");
        }
    }
}
