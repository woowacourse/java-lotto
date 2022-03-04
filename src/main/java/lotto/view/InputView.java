package lotto.view;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int insertMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int lottoMoney = inputInteger();
        printEmptyLine();
        return lottoMoney;
    }

    public static int inputNumberOfManualLottos() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numberOfManualLottos = inputInteger();
        printEmptyLine();
        return numberOfManualLottos;
    }

    public static List<Integer> inputWinningNumbers() {
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
        int bonusNumber = inputInteger();
        printEmptyLine();
        return bonusNumber;
    }

    private static int inputInteger() {
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public static List<List<Integer>> inputManualLottos(int numberOfManualLottos) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottos = IntStream.range(0, numberOfManualLottos)
            .mapToObj(i -> scanner.nextLine())
            .map(manualLotto -> convertStringsToIntegers(splitAndTrim(manualLotto)))
            .collect(toUnmodifiableList());
        printEmptyLine();
        return manualLottos;
    }
}
