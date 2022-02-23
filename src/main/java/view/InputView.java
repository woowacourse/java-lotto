package view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String scanMoney() { //TODO: 투입 금액
        System.out.println("구입금액을 입력해 주세요.");
        return inputFromUser();
    }

    public static String scanWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return inputFromUser();
    }

    private static String inputFromUser() {
        return SCANNER.nextLine();
    }

    public static String scanBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }
}
