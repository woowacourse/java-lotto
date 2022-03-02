package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import utils.IntegerParser;

public class InputView {
    private static final String SEPARATOR = ",";
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입럭해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public int inputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String money = scanner.nextLine();
        return IntegerParser.parseInteger(money);
    }

    public List<Integer> inputWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        List<String> numbers = List.of((scanner.nextLine()).split(SEPARATOR));
        return numbers.stream()
                .map(IntegerParser::parseInteger)
                .collect(Collectors.toList());
    }

    public int inputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        String number = scanner.nextLine();
        return IntegerParser.parseInteger(number);
    }
}
