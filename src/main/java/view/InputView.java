package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";

    public Integer readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return validatePositiveNumber(input);
    }

    public List<Integer> readWinNumbers() {
        System.out.printf("%n지난 주 당첨 번호를 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> splitInput = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
        return splitInput.stream().mapToInt(this::validatePositiveNumber).boxed().toList();
    }

    public Integer readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return validatePositiveNumber(input);
    }

    private Integer validatePositiveNumber(String input) {
        String POSITIVE_INTEGER_REGEX = "[1-9]\\d*";
        if (!input.matches(POSITIVE_INTEGER_REGEX)) {
            throw new IllegalArgumentException("양의 정수를 입력해주세요.");
        }
        return Integer.parseInt(input);
    }
}
