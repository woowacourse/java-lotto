package view;

import java.util.Scanner;

public class InputView {
    public static final String PURCHASE_PRICE_PROMPT = "구입금액을 입력해 주세요.";
    public static final String WINNING_NUMBER_PROMPT = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public String askForBudget() {
        System.out.println(PURCHASE_PRICE_PROMPT);
        return getUserResponse();
    }

    public String askForWinningNumber() {
        System.out.println(WINNING_NUMBER_PROMPT);
        return getUserResponse();
    }

    public String askForBonusNumber() {
        System.out.println(BONUS_NUMBER_PROMPT);
        return getUserResponse();
    }

    private String getUserResponse() {
        return scanner.nextLine();
    }
}