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
        scanner.nextLine();
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return Converter.convertToIntegerList(scanner.nextLine());
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
