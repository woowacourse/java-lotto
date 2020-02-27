package lotto.view.input;

import lotto.parser.GameParser;

import java.util.Scanner;
import java.util.Set;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);
    private static GameParser gameParser = new GameParser();

    public static String inputLottoMoney() {
        System.out.println("구입 금액을 입력하세요");
        return scanner.nextLine();
    }

    public static Set<Integer> inputWinningLotto() {
        System.out.println("지난주 당첨 번호를 입력하세요");
        String input = scanner.nextLine();
        return gameParser.parseInputToNumbers(input);
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요");
        String input = scanner.nextLine();
        return gameParser.parseInputToInt(input);
    }

}
