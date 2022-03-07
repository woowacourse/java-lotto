package lotto.view;

import java.util.Scanner;

import lotto.domain.factory.LottoFactory;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.(1000원 ~ 20억원)");
        return toInteger(scanner.nextLine());
    }

    public static int inputCountManualLotto() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return toInteger(scanner.nextLine());
    }

    public static String inputManualLottoNumber() {
        return scanner.nextLine();
    }

    public static String inputLastWeekWinningNumbers() {
        System.out.printf("%n지난 주 당첨 번호를 입력해 주세요.(\"%s\"를 기준으로 6개 입력해주세요.)%n", LottoFactory.TEXT_DELIMITER);
        return scanner.nextLine();
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return toInteger(scanner.nextLine());
    }

    private static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자를 입력하셔야 합니다.");
        }
    }
}
