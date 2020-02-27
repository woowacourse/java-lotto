package Lotto.views;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    private static Scanner scanner = new Scanner(System.in);

    public static String inputAsPurchaseAmount() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputAsWinningLotto() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        return scanner.nextLine();
    }

    public static String inputAsBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }
}