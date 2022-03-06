package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.model.ticket.buy.ManualBuyCount;
import lotto.model.utils.ManualRandomNumberGenerator;
import lotto.model.utils.NumberGenerator;
import lotto.view.utils.IntegerUtils;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    public static final String REQUEST_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String REQUEST_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String REQUEST_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String NUMBER_DELIMITER = ", ";
    public static final String REQUEST_MANUAL_BUY_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String REQUEST_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";

    // 인스턴스화 방지
    private InputView() {
    }

    public static int requestMoney() {
        System.out.println(REQUEST_MONEY_MESSAGE);
        return IntegerUtils.parse(scanner.nextLine());
    }

    public static List<Integer> requestWinningNumber() {
        System.out.println(REQUEST_WINNING_NUMBERS_MESSAGE);
        return Arrays.stream(Stream.of(scanner
                .nextLine()
                .split(NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .toArray())
                .boxed()
                .collect(Collectors.toList());
    }

    public static int requestBonusBall() {
        System.out.println(REQUEST_BONUS_BALL_MESSAGE);
        return IntegerUtils.parse(scanner.nextLine());
    }

    public static int requestManualBuyCount() {
        System.out.println(REQUEST_MANUAL_BUY_COUNT);
        return IntegerUtils.parse(scanner.nextLine());
    }

    public static List<List<Integer>> requestManualTickets(
            NumberGenerator numberGenerator, ManualBuyCount manualBuyCount) {
        System.out.println(REQUEST_MANUAL_NUMBERS);
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        while (manualBuyCount.isNotZero()) {
            lottoNumbers.add(numberGenerator.generate(manualBuyCount.getValue(), scanner.nextLine().split(", ")));
            manualBuyCount.decrease();
        }
        return lottoNumbers;
    }
}
