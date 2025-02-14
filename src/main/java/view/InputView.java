package view;

import static model.ExceptionMessage.INVALID_INPUT_NULL_OR_BLANK;
import static model.ExceptionMessage.INVALID_INPUT_TYPE;

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
        return Lotto.of(getInput());
    }

    public static Bonus getWinningBonus(final Lotto lotto) {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Bonus.of(getIntegerInput(), lotto);
    }

    private static int getIntegerInput() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_INPUT_TYPE.getMessage());
        }
    }

    private static String getInput() {
        try {
            return sc.nextLine().strip();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(INVALID_INPUT_NULL_OR_BLANK.getMessage());
        }
    }
}
