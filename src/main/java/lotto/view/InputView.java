package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import lotto.domain.vo.LottoNumber;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = SCANNER.nextLine();
        validateBlank(input);
        return validateNumber(input);
    }

    public static int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = SCANNER.nextLine();
        validateBlank(input);
        int inputNumber = validateNumber(input);
        validateNotNegative(inputNumber);
        return inputNumber;
    }

    public static List<LottoNumber> inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = SCANNER.nextLine();
        validateBlank(input);
        return convertToNumbers(input);
    }

    private static List<LottoNumber> convertToNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(InputView::validateNumber)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = SCANNER.nextLine();
        validateBlank(input);
        return new LottoNumber(validateNumber(input));
    }

    private static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없다.");
        }
    }

    private static int validateNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값은 숫자이어야 한다.");
        }
    }

    private static void validateNotNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("입력값은 음수일 수 없다.");
        }
    }

}
