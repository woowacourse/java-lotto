package lotto.view;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int insertMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<Integer> inputWinningNumbers() {
        scanner.nextLine();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = scanner.nextLine();
        return convertStringsToIntegers(splitAndTrim(winningNumbers));
    }

    private static List<Integer> convertStringsToIntegers(List<String> strings) {
        return strings.stream()
            .map(Integer::parseInt)
            .collect(toUnmodifiableList());
    }

    private static List<String> splitAndTrim(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(","))
            .map(String::trim)
            .collect(toUnmodifiableList());
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }
}
