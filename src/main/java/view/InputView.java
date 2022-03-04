package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final String IS_NULL_OR_BLANK_ERROR_MESSAGE = "입력값이 확인되지 않습니다.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_SELF_TICKET_COUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_SELF_TICKET_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "\n지난 주 당첨 번호를 입럭해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "\n보너스 볼을 입력해 주세요.";
    private static final String LOTTO_NUMBER_SPLIT_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        String userInput = inputLine();
        return parseNumber(userInput);
    }

    public static int inputSelfTicketCount() {
        System.out.println(INPUT_SELF_TICKET_COUNT_MESSAGE);
        return parseNumber(inputLine());
    }

    public static List<Set<Integer>> inputSelfTicketNumbers(int selfTicketCount) {
        System.out.println(INPUT_SELF_TICKET_MESSAGE);
        List<Set<Integer>> lottoTicketNumbers = new ArrayList<>();
        for (int i = 0; i < selfTicketCount; i++) {
            String userInput = inputLine();
            lottoTicketNumbers.add(parseLottoNumbers(userInput));
        }
        return lottoTicketNumbers;
    }

    private static Set<Integer> parseLottoNumbers(String userInput) {
        Set<Integer> lottoNumbers = new LinkedHashSet<>();
        List<String> splitInputData = splitInputData(userInput);
        splitInputData.forEach(numberString -> lottoNumbers.add(Integer.valueOf(numberString)));
        return lottoNumbers;
    }

    public static Set<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String userInput = inputLine();
        return parseLottoNumbers(userInput);
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String userInput = inputLine();
        return parseNumber(userInput.trim());
    }

    private static List<String> splitInputData(String inputData) {
        return Arrays.stream(inputData.split(LOTTO_NUMBER_SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static String inputLine() {
        String userInput = scanner.nextLine();
        if (isNullOrBlank(userInput)) {
            throw new IllegalArgumentException(IS_NULL_OR_BLANK_ERROR_MESSAGE);
        }
        return userInput;
    }

    private static boolean isNullOrBlank(String inputText) {
        return inputText == null || inputText.isBlank();
    }

    private static int parseNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}
