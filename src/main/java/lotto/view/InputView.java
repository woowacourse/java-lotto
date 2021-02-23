package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASING_PRICE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_TICKET_COUNTS_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_TICKET_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String COMMA_DELIMITER = ", ";
    private static final int SPLIT_LIMIT_THRESHOLD = -1;

    private InputView() {
    }

    public static int inputPurchasingPrice() {
        return nextIntWithInstructionMessage(INPUT_PURCHASING_PRICE_MESSAGE);
    }

    private static int nextIntWithInstructionMessage(String instructionMessage) {
        System.out.println(instructionMessage);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<Integer> inputWinningTicketNumbers() {
        System.out.println(INPUT_WINNING_TICKET_NUMBERS_MESSAGE);
        return Arrays.stream(SCANNER.nextLine().split(COMMA_DELIMITER, SPLIT_LIMIT_THRESHOLD))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusBallNumber() {
        return nextIntWithInstructionMessage(INPUT_BONUS_BALL_NUMBER_MESSAGE);
    }

    public static int inputManualTicketCounts() {
        System.out.println(INPUT_MANUAL_TICKET_COUNTS_MESSAGE);
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<List<Integer>> inputManualLottoNumbers(int manualTicketCounts) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
        List<List<Integer>> numberGroup = new ArrayList<>();
        for (int i = 0; i < manualTicketCounts; i++) {
            numberGroup.add(Arrays.stream(SCANNER.nextLine().split(COMMA_DELIMITER, SPLIT_LIMIT_THRESHOLD))
            .map(Integer::parseInt)
            .collect(Collectors.toList()));
        }
        return numberGroup;
    }
}
