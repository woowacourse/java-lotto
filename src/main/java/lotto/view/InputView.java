package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String COMMA = ",";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() { /* prevent creating InputView instance */ }

    public static String requestPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String requestWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int requestNumberOfManualLotto() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int lottoCount = scanner.nextInt();
        scanner.nextLine();
        return lottoCount;
    }

    public static List<String> requestManualLotto() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
