package lotto.view;

import lotto.domain.PurchaseAmount;
import lotto.domain.WinningRule;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_PURCHASE_NUMBER_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE = "Invalid type input.";

    public static PurchaseAmount inputPurchaseMoney() {
        try {
            System.out.println(INPUT_PURCHASE_NUMBER_MESSAGE);
            return PurchaseAmount.calculate(Integer.parseInt(SCANNER.nextLine()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PURCHASE_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public static WinningRule inputWinningNumbers() {
        List<Integer> winningNumbers;
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        winningNumbers = Arrays.stream(SCANNER.nextLine().trim().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        int bonusBall = SCANNER.nextInt();
        return new WinningRule(winningNumbers, bonusBall);
    }
}
