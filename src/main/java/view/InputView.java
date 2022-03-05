package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY = -1;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static <T> T getUntilValid(Supplier<T> supplier) {
        T t;
        do {
            t = getFrom(supplier);
        } while (t == null && isRepeatable());
        return t;
    }

    private static <T> T getFrom(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return null;
        }
    }

    private static boolean isRepeatable() {
        String value = inputRepeatOptionFrom( "Y", "N", "y", "n");
        if (value.equals("y") || value.equals("Y")) {
            return true;
        }
        throw new IllegalStateException("시스템을 종료합니다.");
    }

    private static String inputRepeatOptionFrom(String... options) {
        String value;
        do {
            value = inputRepeatOption();
        } while(!isIncludedInOptions(value, options));
        return value;
    }

    private static String inputRepeatOption() {
        System.out.println("다시 시도하시려면 Y, 아니면 N");
        return SCANNER.nextLine();
    }

    private static boolean isIncludedInOptions(String value, String[] options) {
        return Stream.of(options).anyMatch(option -> option.equals(value));
    }

    public static String inputBudget() {
        System.out.println("구입금액을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    private static List<String> splitAndTrim(String text) {
        return trimAll(splitWithEmpty(text));
    }

    private static List<String> trimAll(List<String> tokens) {
        return tokens.stream()
                .map(token -> token.trim())
                .collect(Collectors.toList());
    }

    private static List<String> splitWithEmpty(String text) {
        return Arrays.asList(text.split(DELIMITER, INCLUDE_EMPTY));
    }

    public static String inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static void printManualLottoMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static List<String> inputLottos() {
        return splitAndTrim(SCANNER.nextLine());
    }

    public static List<String> inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return inputLottos();
    }

    public static String inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }
}
