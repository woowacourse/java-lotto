package lotto.view;

import lotto.constant.ErrorMessage;
import lotto.dto.WinningBallsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static lotto.constant.ErrorMessage.*;

public class InputView {

    public static final String PAYMENT_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public int readPayment() {
        System.out.println(PAYMENT_MESSAGE);
        String input = scanner.nextLine();

        return convertToNumber(input);
    }

    public WinningBallsDto readWinningBalls() {
        return new WinningBallsDto(readWinningNumbers(), readBonusNumber());
    }

    private List<Integer> readWinningNumbers() {
        System.out.println(WINNING_NUMBER_MESSAGE);
        String input = scanner.nextLine();

        String[] split = input.split(DELIMITER);
        List<Integer> numbers = new ArrayList<>();
        for (String data : split) {
            numbers.add(convertToNumber(data));
        }
        return numbers;
    }

    private int readBonusNumber() {
        System.out.println(BONUS_NUMBER_MESSAGE);
        String input = scanner.nextLine();

        return convertToNumber(input);
    }

    private static int convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER.getMessage());
        }
    }
}
