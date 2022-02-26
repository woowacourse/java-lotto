package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입급액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public String inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public List<String> inputWinningNumbers() {
        System.out.println();
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        return Arrays.stream(scanner.nextLine()
                .split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String inputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return scanner.nextLine();
    }
}
