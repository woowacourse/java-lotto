package lotto.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String SEPARATOR = ", ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        return translateInteger(scanner.nextLine());
    }

    public List<Integer> getNormalWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        String inputWinningNumbers = scanner.nextLine();

        List<String> strings = Arrays.asList(inputWinningNumbers.split(SEPARATOR));

        return translateIntegerList(strings);
    }

    private List<Integer> translateIntegerList(List<String> values) {
        try {
            return values.stream()
                    .map(Integer::parseInt)
                    .collect(toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수여야 합니다. 형식을 확인해주세요.");
        }
    }

    public int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return translateInteger(scanner.nextLine());
    }

    private int translateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("숫자(정수)를 입력해주세요.");
        }
    }

}
