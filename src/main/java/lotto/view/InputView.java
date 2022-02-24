package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String getMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        return scanner.nextLine();
    }

    public List<Integer> getNormalWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String inputWinningNumbers = scanner.nextLine();

        String[] splitWinningNumbers = inputWinningNumbers.split(", ");

        return Arrays.stream(splitWinningNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

}
