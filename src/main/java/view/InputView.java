package view;

import domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "[ERROR] 숫자를 입력해주세요.";
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String DELIMITER = ", ";
    private static final String INPUT_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int askInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = scanner.nextLine();
        return convertToInt(input);
    }

    public static LottoNumber askInputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        String input = scanner.nextLine();
        int bonusBall = convertToInt(input);
        return LottoNumber.of(bonusBall);
    }

    private static int convertToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    public static Lotto askInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String input = scanner.nextLine();
        return getLotto(input);
    }

    private static Lotto getLotto(String input) {
        String[] numbers = input.split(DELIMITER);
        LottoGenerator manualLottoGenerator = new ManualLottoGenerator(numbers);
        return manualLottoGenerator.generateLotto();
    }

    public static int askManualLottoCount() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT);
        return convertToInt(scanner.nextLine());
    }

    //TODO: List<List<>> 반환하기 고려해보기
    public static Lottos askManualLottoNumbers(int manualLottoCount) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBERS);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            String input = scanner.nextLine();
            lottos.add(getLotto(input));
        }
        return new Lottos(lottos);
    }
}