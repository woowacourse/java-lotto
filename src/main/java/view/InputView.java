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
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입럭해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBER_SPLIT_DELIMITER = ",";

    private static final InputView instance = new InputView();
    private static final Scanner scanner = new Scanner(System.in);
    public static final String INPUT_SELF_TICKET_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";

    private InputView() {
    }

    public int inputPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        String userInput = inputLine();
        return parse(userInput);
    }

    public int inputSelfTicketCount() {
        System.out.println(INPUT_SELF_TICKET_COUNT_MESSAGE);
        return parse(inputLine());
    }

    public List<Set<Integer>> inputSelfTicketNumbers(int selfTicketCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Set<Integer>> lottoTicketNumbers = new ArrayList<>();
        for (int i = 0; i < selfTicketCount; i++) {
            String userInput = inputLine();
            lottoTicketNumbers.add(parseLottoNumbers(userInput));
        }
        return lottoTicketNumbers;
    }

    public Set<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String userInput = inputLine();
        return parseLottoNumbers(userInput);
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String userInput = inputLine();
        return parse(userInput.trim());
    }

    public static InputView getInstance() {
        return instance;
    }

    private List<String> splitInputData(String inputData) {
        return Arrays.stream(inputData.split(LOTTO_NUMBER_SPLIT_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private Set<Integer> parseLottoNumbers(String userInput) {
        Set<Integer> lottoNumbers = new LinkedHashSet<>();
        List<String> splitInputData = splitInputData(userInput);
        splitInputData.forEach(numberString -> lottoNumbers.add(Integer.valueOf(numberString)));
        return lottoNumbers;
    }

    private String inputLine() {
        String userInput = scanner.nextLine();
        if (isNullOrBlank(userInput)) {
            throw new IllegalArgumentException(IS_NULL_OR_BLANK_ERROR_MESSAGE);
        }
        return userInput;
    }

    private boolean isNullOrBlank(String inputText) {
        return inputText == null || inputText.isBlank();
    }

    private static int parse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}
