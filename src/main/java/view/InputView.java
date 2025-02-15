package view;

import exception.CommonExceptionType;
import java.util.List;
import java.util.Scanner;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;

public class InputView {

    static Scanner sc = new Scanner(System.in);

    public static LottoPurchase getPurchaseLotto() {
        System.out.println("구입금액을 입력해 주세요.");
        return LottoPurchase.of(getIntegerInput());
    }

    public static Lotto getWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Lotto.of(getIntegerInputs());
    }

    public static Bonus getWinningBonus(final Lotto lotto) {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Bonus.of(getIntegerInput(), lotto);
    }


    private static int getIntegerInput() {
        return InputParser.of(getInput()).getInput();
    }

    private static List<Integer> getIntegerInputs() {
        return InputParser.of(getInput()).getInputs();
    }

    private static String getInput() {
        try {
            return sc.nextLine().strip();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(CommonExceptionType.INVALID_INPUT_NULL_OR_BLANK.getMessage());
        }
    }
}
