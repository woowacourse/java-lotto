package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import utils.Validator;

public class InputView {

    private static final String INPUT_NUMBER_DELIMITER = ",";
    private static final int INPUT_NUMBER_SPLIT_OPTION = -1;
    private static final String INPUT_MONEY_INSTRUCTION = "구입금액을 입력해 주세요.";
    private static final String INPUT_WIN_LOTTO_INSTRUCTION = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_INSTRUCTION = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_COUNT_INSTRUCTION = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_INSTRUCTION = "수동으로 구매할 번호를 입력해 주세요.";
    private static final Scanner SCANNER = new Scanner(System.in);

    private static String getInput() {
        return SCANNER.nextLine();
    }

    public int getMoney() {
        System.out.println(INPUT_MONEY_INSTRUCTION);
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return Integer.parseInt(input);
    }

    public List<List<String>> getManualLotto(final int manualCount) {
        System.out.println();
        System.out.println(INPUT_MANUAL_LOTTO_INSTRUCTION);
        return IntStream.range(0, manualCount)
            .mapToObj(i -> getLotto())
            .collect(Collectors.toList());
    }

    private List<String> getLotto() {
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        return Arrays.stream(input.split(INPUT_NUMBER_DELIMITER, INPUT_NUMBER_SPLIT_OPTION))
            .map(String::trim)
            .collect(Collectors.toList());
    }

    public List<String> getWinLotto() {
        System.out.println();
        System.out.println(INPUT_WIN_LOTTO_INSTRUCTION);
        return getLotto();
    }

    public int getBonusNumber() {
        System.out.println();
        System.out.println(INPUT_BONUS_INSTRUCTION);
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return Integer.parseInt(input);
    }

    public int getManualCount() {
        System.out.println();
        System.out.println(INPUT_MANUAL_COUNT_INSTRUCTION);
        final String input = getInput();
        Validator.checkNullOrEmpty(input);
        Validator.checkFormat(input);
        return Integer.parseInt(input);
    }
}
