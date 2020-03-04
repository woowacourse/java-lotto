package lotto.view;

import lotto.exception.NotNumberException;
import lotto.exception.NullOrEmptyException;
import lotto.model.LottoNumber;
import lotto.model.LottoTicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String EMPTY_STRING = "";
    private static final String NULL_OR_EMPTY_EXCEPTION_MESSAGE = "null 혹은 빈문자열을 입력할 수 없습니다.";
    private static final String NUMBER_FORMAT_EXCEPTION_MESSAGE = "숫자를 입력하세요.";

    private static Scanner scanner = new Scanner(System.in);

    public static int inputPayment() {
        String input = scanner.nextLine();
        checkNullOrEmptyInput(input);
        checkNumberFormat(input);
        int payment = Integer.parseInt(input);
        return payment;
    }

    public static LottoNumber inputBonusBall() {
        String input = scanner.nextLine();
        checkNullOrEmptyInput(input);
        checkNumberFormat(input);
        return LottoNumber.lottoNumber(Integer.parseInt(input));
    }

    // 수동
    public static LottoTicket inputLottoTicket() {
        String input = scanner.nextLine();
        checkNullOrEmptyInput(input);
        return makeNumbers(splitInput(input));
    }

    public static int inputManualCount() {
        String input = scanner.nextLine();
        checkNullOrEmptyInput(input);
        checkNumberFormat(input);
        return Integer.parseInt(input);
    }

    public static LottoTicket inputWinNumber() {
        String inputs = scanner.nextLine();
        checkNullOrEmptyInput(inputs);
        return makeNumbers(splitInput(inputs));
    }

    public static void checkNullOrEmptyInput(String input) {
        if (input == null || input.equals(EMPTY_STRING)) {
            throw new NullOrEmptyException(NULL_OR_EMPTY_EXCEPTION_MESSAGE);
        }
    }

    public static List<String> splitInput(String numbers) {
        return Arrays.asList(numbers.split(","));
    }

    public static LottoTicket makeNumbers(List<String> inputs) {
        List<LottoNumber> numbers = new ArrayList<>();
        for (String input : inputs) {
            input = input.trim();
            checkNumberFormat(input);
            LottoNumber number = LottoNumber.lottoNumber(Integer.parseInt(input));
            numbers.add(number);
        }
        return new LottoTicket(numbers);
    }

    public static void checkNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NotNumberException(NUMBER_FORMAT_EXCEPTION_MESSAGE);
        }
    }
}
