package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static int inputPurchasingPassiveLottoNumber() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<Integer> inputPurchasingPassiveLotto() {
        return inputLotto();
    }

    public static List<Integer> inputWinningLotto() {
        System.out.println("지난 주 당첨 로또를 입력해주세요.");
        return inputLotto();
    }

    private static List<Integer> inputLotto() {
        String input = SCANNER.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::new)
                .collect(Collectors.toList());
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        return SCANNER.nextInt();
    }
}
