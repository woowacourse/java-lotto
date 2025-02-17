package lotto.view;

import java.util.ArrayList;
import java.util.List;

public class InputView {

    public static final String COMMA = ",";

    public int requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = parseInt(Console.readLine());
        validatePositive(purchaseAmount);
        return purchaseAmount;
    }

    public List<Integer> requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        final String input = Console.readLine();
        return parseWinningNumbers(input);
    }

    private List<Integer> parseWinningNumbers(final String input) {
        final String[] split = splitWithRegex(input, COMMA);
        final List<Integer> numbers = convertToIntegers(split);
        return List.copyOf(numbers);
    }

    private String[] splitWithRegex(final String value, final String regex) {
        return value.split(regex);
    }

    private List<Integer> convertToIntegers(final String[] split) {
        final List<Integer> numbers = new ArrayList<>();
        for (String token : split) {
            int number = parseInt(token);
            validatePositive(number);
            numbers.add(number);
        }
        return List.copyOf(numbers);
    }

    public int requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        final String input = Console.readLine();
        return parseBonusNumber(input);
    }

    private int parseBonusNumber(final String input) {
        int bonusNumber = parseInt(input);
        validatePositive(bonusNumber);
        return bonusNumber;
    }

    private int parseInt(final String value) {
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
