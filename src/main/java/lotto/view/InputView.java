package lotto.view;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static int inputBuyMoney() {
        System.out.println("구입금액을 입력해주세요");
        return inputNumber();
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력하세요.");
        return inputNumber();
    }

    private static int inputNumber() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return inputNumber();
        }
    }

    public static int inputManualPurchaseCount() {
        try {
            System.out.println("수동으로 구매할 로또 수를 입력하세요");
            return Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException e) {
            return inputManualPurchaseCount();
        }
    }

    public static List<Integer> inputManualNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        return inputNumbers();
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        return inputNumbers();
    }

    private static List<Integer> inputNumbers() {
        try {
            List<String> strings = Arrays.asList(SCANNER.nextLine().split(","));
            return strings.stream()
                    .map(String::trim)
                    .map(Integer::new)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            System.out.println("다시 입력하세요");
            return inputManualNumbers();
        }
    }
}
