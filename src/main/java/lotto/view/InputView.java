package lotto.view;

import java.util.Scanner;
import lotto.utils.IntegerUtils;

public class InputView {

    private static final String DELIMITER = ",";
    private static final int SPLIT_LIMIT = -1;

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        return IntegerUtils.parse(input);
    }

    public String[] inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        return input.split(DELIMITER, SPLIT_LIMIT);
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return IntegerUtils.parse(input);
    }
}
