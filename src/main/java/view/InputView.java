package view;

import java.util.Scanner;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;

public class InputView {

    static Scanner sc = new Scanner(System.in);

    public static LottoPurchase getPurchaseLotto() {
        System.out.println("구입금액을 입력해 주세요.");
        return LottoPurchase.of(getInput());
    }

    public static Lotto getWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Lotto.of(getInput());
    }

    public static Bonus getWinningBonus(final Lotto lotto) {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Bonus.of(getInput(), lotto);
    }

    private static String getInput() {
        return sc.nextLine();
    }
}
