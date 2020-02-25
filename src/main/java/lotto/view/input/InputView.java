package lotto.view.input;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static String inputLottoMoney() {
        System.out.println("구입 금액을 입력하세요");
        return scanner.nextLine();
    }

    public static String inputWinningLotto() {
        System.out.println("지난주 당첨 번호를 입력하세요");
        return scanner.nextLine();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        return scanner.nextLine();
    }

}
