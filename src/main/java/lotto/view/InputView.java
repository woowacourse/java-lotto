package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);
    public static final String DELIMITER = ",";
    public static final String INPUT_MONEY = "구입금액을 입력해 주세요.";

    public static int inputMoney() {
        OutputView.printInputMessage(INPUT_MONEY);
        return scanner.nextInt();
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요");
        scanner.nextLine();
        String rawWinningNumbers = scanner.nextLine();
        String[] splittedWinningNumbers = rawWinningNumbers.split(DELIMITER);
        return Arrays.stream(splittedWinningNumbers)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요");
        return scanner.nextInt();
    }
}
