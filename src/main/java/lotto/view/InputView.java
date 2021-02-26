package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

import lotto.domain.lotto.LottoNumber;

import static lotto.utils.Validation.isNumericAndPositive;

public class InputView {
    private static final String DELIMITER = ",";
    private static final String ERROR_NOT_NUMERIC_NEGATIVE = "[ERROR] 해당 값은 양수인 숫자여야 합니다.";

    public static final String REQUEST_PURCHASE_MONEY = "구입금액을 입력해주세요.";

    public static final String REQUEST_LAST_WIN_BONUS_BALL = "보너스 볼을 입력해 주세요.";
    public static final String REQUEST_NUMBER_OF_BUYING_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 로또 수를 입력해 주세요.";

    public static final String REQUEST_LAST_WIN_LOTTO_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String REQUEST_INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";


    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int getPositiveNumberInput(String message) {
        System.out.println(message);
        String input = scanner.nextLine();

        if (!isNumericAndPositive(input)) {
            throw new IllegalArgumentException(ERROR_NOT_NUMERIC_NEGATIVE);
        }

        return Integer.parseInt(input);
    }

    public static List<Integer> getSplitLottoNumbers(String message) {
        System.out.println(message);
        String lottoNumbersInput = scanner.nextLine();
        String[] splitLottoNumbersInput = lottoNumbersInput.replace(" ", "").split(DELIMITER);
        return Arrays.stream(splitLottoNumbersInput).map(Integer::parseInt).collect(Collectors.toList());



    }
}