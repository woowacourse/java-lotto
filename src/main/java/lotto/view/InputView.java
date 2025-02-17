package lotto.view;

import static lotto.util.Constant.LOTTO_NUMBER_DELIMITER;
import static lotto.util.ErrorHandler.INVALID_LOTTO_MONEY_NUMBER;
import static lotto.util.ErrorHandler.INVALID_LOTTO_NUMBER;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readLottoMoney() {
        String message = "구입금액을 입력해 주세요.";
        String response = readResponse(message);
        return validateAndParseLottoMoney(response);
    }

    public static Set<Integer> readWinningNumbers() {
        String message = "지난 주 당첨 번호를 입력해 주세요.";
        return parseLottoNumbers(readResponse(message));
    }

    public static String readBonusBall() {
        String message = "보너스 볼을 입력해 주세요.";
        return readResponse(message);
    }

    private static String readResponse(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    public static int validateAndParseLottoMoney(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw INVALID_LOTTO_MONEY_NUMBER.getException();
        }
    }

    public static Set<Integer> parseLottoNumbers(String input) {
        Set<String> result = Arrays.stream(input.split(LOTTO_NUMBER_DELIMITER)).collect(Collectors.toSet());
        return result.stream()
                .map(s -> validateAndParseLottoNumbers(s.strip()))
                .collect(Collectors.toSet());
    }

    public static int validateAndParseLottoNumbers(String lottoNumberInput) {
        try {
            return Integer.parseInt(lottoNumberInput);
        } catch (NumberFormatException e) {
            throw INVALID_LOTTO_NUMBER.getException();
        }
    }
}
