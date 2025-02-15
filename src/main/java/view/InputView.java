package view;

import java.util.Scanner;

public class InputView {

    private static final String ASK_LOTTO_MONEY = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";

    private static InputView instance;

    private final Scanner scanner = new Scanner(System.in);

    public static InputView getInstance() {
        if (instance == null) {
            instance = new InputView();
        }

        return instance;
    }

    public String askWinningNumber() {
        System.out.println(ASK_WINNING_NUMBER);
        return getUserResponse();
    }
    public String askBonusNumber() {
        System.out.println(ASK_BONUS_NUMBER);
        return getUserResponse();
    }

    public String askForNormal() {
        System.out.println(ASK_LOTTO_MONEY);
        return getUserResponse();
    }

    private String getUserResponse() {
        return scanner.nextLine();
    }
}
