package view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import java.util.stream.IntStream;
import utils.IntegerParser;

public class InputView {
    private static final String SEPARATOR = ",";
    private static final String INPUT_PURCHASE_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "\n지난 주 당첨 번호를 입럭해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public int inputPurchaseMoney() {
        System.out.println(INPUT_PURCHASE_MONEY_MESSAGE);
        String purchaseMoney = scanner.nextLine();
        return IntegerParser.parseInteger(purchaseMoney);
    }

    public int inputManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT_MESSAGE);
        String manualLottoCount = scanner.nextLine();
        return IntegerParser.parseInteger(manualLottoCount);
    }

    public List<List<Integer>> inputManualLottoNumberGroups(final int manualLottoCount) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS_MESSAGE);
        return IntStream.range(0, manualLottoCount)
                .mapToObj(index -> inputManualLottoNumbers())
                .collect(Collectors.toList());
    }

    private List<Integer> inputManualLottoNumbers() {
        List<String> numbers = List.of((scanner.nextLine()).split(SEPARATOR));
        return numbers.stream()
                .map(IntegerParser::parseInteger)
                .collect(Collectors.toList());
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
