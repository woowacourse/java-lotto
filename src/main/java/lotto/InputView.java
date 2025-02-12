package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner in = new Scanner(System.in);

    public int requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = in.nextInt();
        validatePositive(purchaseAmount);
        return purchaseAmount;
    }

    public List<Integer> requestWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String rawInput = in.next();
        if (rawInput == null || rawInput.isBlank()) {
            throw new IllegalArgumentException("값이 비어있습니다.");
        }
        String[] split = rawInput.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String s : split) {
            int number = parseInt(s);
            validatePositive(number);
            numbers.add(number);
        }
        return List.copyOf(numbers);
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수일 수 없습니다.");
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
    }
}
