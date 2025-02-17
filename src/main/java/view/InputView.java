package view;

import static view.DisplayConstants.BONUS_NUMBER_PROMPT;
import static view.DisplayConstants.PURCHASE_PRICE_PROMPT;
import static view.DisplayConstants.WINNING_NUMBER_PROMPT;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String askForBudget() {
        System.out.println(PURCHASE_PRICE_PROMPT.getFormat());
        return getUserResponse();
    }

    public String askForWinningNumber() {
        System.out.println(WINNING_NUMBER_PROMPT.getFormat());
        return getUserResponse();
    }

    public String askForBonusNumber() {
        System.out.println(BONUS_NUMBER_PROMPT.getFormat());
        return getUserResponse();
    }

    private String getUserResponse() {
        return scanner.nextLine();
    }
}