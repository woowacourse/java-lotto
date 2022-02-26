package view;

import static validator.NumberValidators.validateAndParseNumber;
import static view.OutputView.LINE_SEPARATOR;
import static view.OutputView.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String REQUEST_TOTAL_LOTTO_PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String REQUEST_TOTAL_MANUAL_LOTTO_COUNT_INPUT_MESSAGE = LINE_SEPARATOR + "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_MANUAL_LOTTOS_INPUT_MESSAGE = LINE_SEPARATOR + "수동으로 구매할 번호를 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int requestTotalLottoPrice() {
        print(REQUEST_TOTAL_LOTTO_PRICE_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    public static int requestManualLottoCount() {
        print(REQUEST_TOTAL_MANUAL_LOTTO_COUNT_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    public static List<String> requestManualLottos(int count) {
        print(REQUEST_MANUAL_LOTTOS_INPUT_MESSAGE);

        List<String> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(readline());
        }

        return lottos;
    }

    public static String requestWinningNumbers() {
        print(REQUEST_WINNING_NUMBERS_INPUT_MESSAGE);
        return readline();
    }

    public static int requestBonusNumber() {
        print(REQUEST_BONUS_BALL_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    private static String readline() {
        return scanner.nextLine();
    }
}
