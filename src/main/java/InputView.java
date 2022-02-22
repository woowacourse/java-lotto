import java.util.Scanner;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static Scanner scanner = new Scanner(System.in);

    public static String askInputMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public static String askInputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        return scanner.nextLine();
    }

    public static String askInputBonusBall() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return scanner.nextLine();
    }
}
//구입금액을 입력해 주세요.
//지난 주 당첨 번호를 입력해 주세요.
//보너스 볼을 입력해 주세요.