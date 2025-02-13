package lotto.view;

import lotto.util.Converter;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static long inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLong();
    }

    public static List<Integer> inputWinningNumbers() {
        return Converter.convertToIntegerList(scanner.nextLine());
    }
}
