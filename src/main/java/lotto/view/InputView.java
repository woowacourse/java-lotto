package lotto.view;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static long insertMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        long lottoMoney = inputLong();
        printEmptyLine();
        return lottoMoney;
    }

    public static int inputNumberOfManualLottos() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numberOfManualLottos = inputInteger();
        printEmptyLine();
        return numberOfManualLottos;
    }

    public static List<Set<Integer>> inputManualLottos(int numberOfManualLottos) {
        if (numberOfManualLottos == 0) {
            return List.of();
        }
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Set<Integer>> manualLottos = IntStream.range(0, numberOfManualLottos)
            .mapToObj(i -> scanner.nextLine())
            .map(manualLotto -> convertStringsToIntegers(splitAndTrim(manualLotto)))
            .collect(toList());
        printEmptyLine();
        return List.copyOf(manualLottos);
    }

    public static Set<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbers = scanner.nextLine();
        return convertStringsToIntegers(splitAndTrim(winningNumbers));
    }

    private static Set<Integer> convertStringsToIntegers(List<String> strings) {
        return strings.stream()
            .map(Integer::parseInt)
            .collect(toUnmodifiableSet());
    }

    private static List<String> splitAndTrim(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(","))
            .map(String::trim)
            .collect(toUnmodifiableList());
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = inputInteger();
        printEmptyLine();
        return bonusNumber;
    }

    private static int inputInteger() {
        int result = scanner.nextInt();
        validatePositive(result);
        scanner.nextLine();
        return result;
    }

    private static long inputLong() {
        long result = scanner.nextLong();
        validatePositive(result);
        scanner.nextLine();
        return result;
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    private static void validatePositive(long input) {
        if (input < 0) {
            throw new IllegalArgumentException("0 이상의 정수를 입력해주세요.");
        }
    }
}
