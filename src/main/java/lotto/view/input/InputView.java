package lotto.view.input;

import lotto.utils.InputUtils;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputMoney() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            return InputCheck.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputMoney();
        }
    }

    public static List<Integer> inputWinningLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            return InputUtils.parseIntegerList(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputWinningLotto();
        }
    }

    public static int inputBonusNum() {
        try {
            System.out.println("보너스 번호를 입력해주세요.");
            return InputCheck.parseInteger(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputBonusNum();
        }
    }
}
