package view;

import java.util.Scanner;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static LottoPurchase getPurchaseLotto() {
        return LottoPurchase.of(getInput());
    }

    public static Lotto getWinningLotto() {
        return Lotto.of(getInput());
    }

    public static Bonus getWinningBonus(final Lotto lotto) {
        return Bonus.of(getInput(), lotto);
    }

    private static String getInput() {
        return scanner.nextLine();
    }
}
