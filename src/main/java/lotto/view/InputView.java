package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.vo.Money;
import lotto.domain.vo.LottoNumber;

public class InputView {

    private static final String DELIMITER = ",";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(stringToInt(validateBlank(SCANNER.nextLine())));
    }

    public static int inputPassivityLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return stringToInt(validateBlank(SCANNER.nextLine()));
    }

    public static List<Lotto> inputPassivityLottoNumbers(final int amount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        return IntStream.range(0, amount)
            .mapToObj(i -> new Lotto(convertToNumbers(validateBlank(SCANNER.nextLine()))))
            .collect(Collectors.toList());
    }

    public static List<LottoNumber> inputWinnerNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return convertToNumbers(validateBlank(SCANNER.nextLine()));
    }

    public static LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return LottoNumber.of(stringToInt(validateBlank(SCANNER.nextLine())));
    }

    private static List<LottoNumber> convertToNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
            .map(number -> LottoNumber.of(stringToInt(number.trim())))
            .collect(Collectors.toList());
    }

    private static String validateBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("입력값은 비어있을 수 없다.");
        }
        return input;
    }

    private static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입금액은 숫자이어야 한다.");
        }
    }
}
