package view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String receivePurchaseMoney() {
        String input = receiveInput("구입금액을 입력해 주세요.");
        System.out.println();
        return input;
    }

    public static int receiveManualTicketQuantity() {
        String input = receiveInput("수동으로 구매할 로또 수를 입력해 주세요.");
        System.out.println();
        return createNumber(input);
    }

    public static List<List<Integer>> receiveExpectedManualNumbers(final int manualLottoQuantity) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<List<Integer>> expectedManualNumbers = IntStream.range(0, manualLottoQuantity)
                .mapToObj(i -> createNumbers(SCANNER.nextLine()))
                .collect(Collectors.toList());

        System.out.println();
        return expectedManualNumbers;
    }

    private static String receiveInput(final String message) {
        System.out.println(message);
        String input = SCANNER.nextLine();
        return checkNull(input).trim();
    }

    private static String checkNull(final String value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("null 이 입력되었습니다.");
        }
        return value;
    }

    public static List<Integer> receiveWinningNumbers() {
        String input = receiveInput("지난 당첨 번호를 입력해 주세요.");
        return createNumbers(input);
    }

    private static List<Integer> createNumbers(final String value) {
        try {
            return Arrays.stream(value.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }

    public static int receiveBonusNumber() {
        String input = receiveInput("보너스 볼을 입력해 주세요.");
        System.out.println();
        return createNumber(input);
    }

    private static int createNumber(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수가 아닙니다.");
        }
    }
}
