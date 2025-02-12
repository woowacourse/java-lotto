package lotto.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int validatedMoney = validateNumber(scanner.nextLine());
        validateUnit(validatedMoney);
        validateNegative(validatedMoney);
        validateLimit(validatedMoney);
        return validatedMoney;
    }

    private int validateNumber(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 숫자여야 합니다.");
        }
    }
    private void validateUnit(int validatedMoney) {
        if (validatedMoney % 1000 != 0) {
            throw new IllegalArgumentException("구입금액은 천원 단위여야 합니다.");
        }
    }
    private void validateNegative(int validatedMoney) {
        if (validatedMoney <= 0) {
            throw new IllegalArgumentException("구입금액은 천원부터입니다");
        }
    }
    private void validateLimit(int validatedMoney) {
        if (validatedMoney > 100000) {
            throw new IllegalArgumentException("10만원까지 구매 가능합니다.");
        }
    }
}
