package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Integer insertMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public static List<Integer> insertWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = scanner.nextLine();
        return convertStringsToIntegers(splitAndTrim(winningNumbers));
    }

    private static List<Integer> convertStringsToIntegers(List<String> strings) {
        return strings.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<String> splitAndTrim(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
