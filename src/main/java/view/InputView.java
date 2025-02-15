package view;

import static constant.InputViewMessage.BONUS_GUIDANCE;
import static constant.InputViewMessage.LOTTO_PURCHASE_GUIDANCE;
import static constant.InputViewMessage.LOTTO_GUIDANCE;

import java.util.Scanner;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;

public class InputView {

    static Scanner scanner = new Scanner(System.in);

    public static LottoPurchase getPurchaseLotto() {
        print(LOTTO_PURCHASE_GUIDANCE.getMessage());
        return LottoPurchase.of(getInput());
    }

    public static Lotto getWinningLotto() {
        print(LOTTO_GUIDANCE.getMessage());
        return Lotto.of(getInput());
    }

    public static Bonus getWinningBonus(final Lotto lotto) {
        print(BONUS_GUIDANCE.getMessage());
        return Bonus.of(getInput(), lotto);
    }

    private static String getInput() {
        return scanner.nextLine();
    }

    private static void print(final String output) {
        System.out.println(output);
    }
}
