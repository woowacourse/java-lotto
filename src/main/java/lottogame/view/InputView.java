package lottogame.view;

import lottogame.domain.Money;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String getPrice() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static int getNumberOfMannualTicket(Money money) {
        String input;

        do{
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            input = SCANNER.nextLine();
        } while(money.isInvalidNumber(input));

        return Integer.parseInt(input);
    }

    public static String getManualLottoNumber() {
        return SCANNER.nextLine();
    }
}
