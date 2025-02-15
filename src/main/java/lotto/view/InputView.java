package lotto.view;

import lotto.util.Converter;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static long inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();

        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구매 금액은 정수만 입력 가능합니다");
        }
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return Converter.convertToIntegerList(scanner.nextLine());
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return Converter.convertToInteger(input);
    }
}
