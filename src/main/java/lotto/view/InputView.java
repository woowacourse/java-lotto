package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String COMMA = ",";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputForLottoMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        try {
            return inputDecimal();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    public int inputForManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            return inputDecimal();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자여야 합니다.");
        }
    }

    public void printForInputManualLottoNumbersMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요");
    }

    public List<Integer> inputForLottoNumbers() {
        return inputToIntegerList();
    }

    public List<Integer> inputForWinningNumbers() {
        System.out.println("지난주 당첨번호를 입력해주세요.");
        return inputToIntegerList();
    }

    public int inputForBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        return inputDecimal();
    }

    private int inputDecimal() {
        return Integer.parseInt(scanner.nextLine());
    }

    private List<Integer> inputToIntegerList() {
        String input = scanner.nextLine();
        List<String> splitNumbers = Arrays.asList(input.split(COMMA));
        List<String> trimNumbers = splitNumbers.stream().map(String::trim)
            .collect(Collectors.toList());

        return stringsToIntegers(trimNumbers);
    }

    private List<Integer> stringsToIntegers(List<String> strings) {
        try {
            return strings.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자가 포함되어 있습니다.");
        }
    }
}
