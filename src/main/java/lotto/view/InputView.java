package lotto.view;

import java.util.Optional;
import java.util.Scanner;

public class InputView {
    private static final String BLANK = "";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static int inputBuyMoney() {
        System.out.println("구입금액을 입력해주세요");
        return inputNumber();
    }

    public static int inputManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 개수를 입력하세요");
        return inputNumber();
    }

    public static int inputBonusLottoNumber() {
        System.out.println("보너스 볼 번호를 입력하세요");
        return inputNumber();
    }

    private static int inputNumber() {
        try {
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return inputNumber();
        }
    }

    public static String inputManualNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요. ex) 1, 2, 3, 4, 5, 6");
        return input();
    }

    public static String inputWinningLotto() {
        System.out.println("지난 주 당첨번호들을 입력해 주세요.");
        return input();
    }

    private static String input() {
        Optional<String> optInput = Optional.ofNullable(SCANNER.nextLine());
        return optInput.orElse(BLANK);
    }
}
