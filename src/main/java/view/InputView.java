package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String insertPayment() {
        OutputView.printGuideMessage("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> insertLotto() {
        OutputView.printGuideMessage("지난 주 당첨 번호를 입력해 주세요.");
        String inputValue = scanner.nextLine();
        String[] numbers = inputValue.split(",");
        return Arrays.stream(numbers)
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    public static String insertBonus() {
        OutputView.printGuideMessage("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
