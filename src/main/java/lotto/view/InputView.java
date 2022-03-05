package lotto.view;

import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = input();
        return validateNumber(input);
    }

    public static int inputManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = input();
        int inputNumber = validateNumber(input);
        validateNotNegative(inputNumber);
        return inputNumber;
    }

    public static List<Lotto> inputManualLottos(int manualLottoCount) {
        if (manualLottoCount != 0) {
            System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
        }
        return getManualLottos(manualLottoCount);
    }

    private static List<Lotto> getManualLottos(int manualLottoCount) {
        return IntStream.range(0, manualLottoCount)
                .mapToObj((value) -> new Lotto(inputLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> inputLottoNumbers() {
        String input = input();
        return convertToNumbers(input);
    }

    public static List<LottoNumber> inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = input();
        return convertToNumbers(input);
    }

    public static List<LottoNumber> convertToNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(InputView::validateNumber)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new LottoNumber(validateNumber(input()));
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

    private static String input() {
        String input = SCANNER.nextLine();
        validateBlank(input);
        return input;
    }

    public static void validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없다.");
        }
    }
}
