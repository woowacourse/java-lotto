package view;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class InputView {

    private static final String NUMBER_DELIMITER = ",";

    private static Scanner scanner = new Scanner(System.in);

    public static int enterMoney() {
        System.out.println("구입 금액을 입력해주세요.");

        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static List<Set<Integer>> enterManualNumbers() {
        int numberOfManualNumbers = enterNumberOfManualNumbers();

        if (numberOfManualNumbers != 0) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }

        List<Set<Integer>> manualNumbers = new ArrayList<>();
        for (int i = 0; i < numberOfManualNumbers; i++) {
            manualNumbers.add(parseStringToInts(scanner.nextLine()));
        }

        return manualNumbers;
    }

    private static int enterNumberOfManualNumbers() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine().trim());
    }

    public static Set<Integer> enterLastWeekWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        return parseStringToInts(scanner.nextLine());
    }

    private static Set<Integer> parseStringToInts(String input) {
        return Arrays.asList(input.split(NUMBER_DELIMITER)).stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toSet());
    }

    public static int enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return Integer.parseInt(scanner.nextLine().trim());
    }
}
