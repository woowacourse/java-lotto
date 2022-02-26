package view;

import static validator.NumberValidators.validateAndParseNumber;
import static view.OutputView.LINE_SEPARATOR;
import static view.OutputView.print;

import domain.LottoCountsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final String REQUEST_TOTAL_LOTTO_PRICE_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String REQUEST_TOTAL_MANUAL_LOTTO_COUNT_INPUT_MESSAGE =
            LINE_SEPARATOR + "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_MANUAL_LOTTOS_INPUT_MESSAGE = LINE_SEPARATOR + "수동으로 구매할 번호를 입력해 주세요.";
    private static final String REQUEST_WINNING_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int requestTotalPrice() {
        print(REQUEST_TOTAL_LOTTO_PRICE_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    public static int requestManualsCount() {
        print(REQUEST_TOTAL_MANUAL_LOTTO_COUNT_INPUT_MESSAGE);
        return validateAndParseNumber(readline());
    }

    public static List<String> requestManualsNumbers(LottoCountsDto countsDto) {
        int manualsCount = countsDto.getManuals();

        if (manualsCount == 0) {
            return new ArrayList<>();
        }

        print(REQUEST_MANUAL_LOTTOS_INPUT_MESSAGE);
        return Stream.generate(InputView::readline)
                .limit(manualsCount)
                .collect(Collectors.toList());
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
