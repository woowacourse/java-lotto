package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.WinningNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String INPUT_MONEY_ONLY_NUMBER_MESSAGE = "[ERROR] 구입 금액은 숫자로만 입력해주세요.";
    private static final String INPUT_MANUAL_GAME_COUNT_ONLY_NUMBER_MESSAGE = "[ERROR] 수동 구매 수량은 숫자로만 입력해주세요.";
    private static final String INPUT_WINNING_NUMBER_ONLY_NUMBER_MESSAGE = "[ERROR] 당첨 번호는 숫자로만 입력해주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBER_ONLY_NUMBER_MESSAGE = "[ERROR] 로또 번호는 숫자로만 입력해주세요.";
    private static final String INPUT_BONUS_BALL_ONLY_NUMBER_MESSAGE = "[ERROR] 보너스 볼은 숫자로만 입력해주세요.";
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_GAME_COUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBER_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String SPLIT_REGEX = ", ";

    private static final Scanner scanner = new Scanner(System.in);

    public static int askInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = scanner.nextLine();
        return convertToInt(input, INPUT_MONEY_ONLY_NUMBER_MESSAGE);
    }

    public static int astInputManualLottoCount() {
        System.out.println(INPUT_MANUAL_GAME_COUNT_MESSAGE);
        String input = scanner.nextLine();
        return convertToInt(input, INPUT_MANUAL_GAME_COUNT_ONLY_NUMBER_MESSAGE);
    }

    public static List<Lotto> askInputManualLottoNumber(int count) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBER_MESSAGE);
        List<Lotto> manualLottoList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String input = scanner.nextLine();
            String[] numbers = input.split(SPLIT_REGEX);
            manualLottoList.add(new Lotto(Arrays.stream(numbers)
                .map(number -> convertToInt(number, INPUT_MANUAL_LOTTO_NUMBER_ONLY_NUMBER_MESSAGE))
                .map(number -> LottoNumber.valueOf(number))
                .collect(Collectors.toList())));
        }

        return manualLottoList;
    }

    public static WinningNumber askInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String input = scanner.nextLine();
        String[] numbers = input.split(SPLIT_REGEX);
        return new WinningNumber(Arrays.stream(numbers)
            .map(number -> convertToInt(number, INPUT_WINNING_NUMBER_ONLY_NUMBER_MESSAGE))
            .map(number -> LottoNumber.valueOf(number))
            .collect(Collectors.toList()));
    }

    public static LottoNumber askInputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        String input = scanner.nextLine();
        int number = convertToInt(input, INPUT_BONUS_BALL_ONLY_NUMBER_MESSAGE);
        LottoNumber bonusBall = LottoNumber.valueOf(number);
        return bonusBall;
    }

    private static int convertToInt(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
