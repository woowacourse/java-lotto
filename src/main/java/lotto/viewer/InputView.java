package lotto.viewer;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int purchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public Object inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요");
        String rawWinningNumbers = scanner.nextLine();
        String[] splittedWinningNumbers = rawWinningNumbers.split(",");
        return Arrays.stream(splittedWinningNumbers)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요");
        return scanner.nextInt();
    }
}
