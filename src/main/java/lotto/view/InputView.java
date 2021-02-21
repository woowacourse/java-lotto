package lotto.view;

import static lotto.view.messages.InputMessages.BONUS_BALL_INPUT_REQUEST;
import static lotto.view.messages.InputMessages.PURCHASE_MONEY_INPUT_REQUEST;
import static lotto.view.messages.InputMessages.WINNING_LOTTO_LINE_INPUT_REQUEST;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMIT = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static int getMoneyUserInput() {
        System.out.println(PURCHASE_MONEY_INPUT_REQUEST.getMessage());
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<Integer> getLottoNumbersUserInput() {
        System.out.println(WINNING_LOTTO_LINE_INPUT_REQUEST.getMessage());
        String LottoNumbersInput = SCANNER.nextLine();
        List<String> parsedLottoNumbersInput = Arrays
            .asList(LottoNumbersInput.replace(" ", "").split(DELIMIT));
        return parsedLottoNumbersInput.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static int getBonusLottoNumberUserInput() {
        System.out.println(BONUS_BALL_INPUT_REQUEST.getMessage());
        return Integer.parseInt(SCANNER.nextLine());
    }

}