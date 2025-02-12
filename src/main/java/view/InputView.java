package view;

import java.util.Scanner;
import model.Bonus;
import model.Lotto;

public class InputView {

    static Scanner sc = new Scanner(System.in);

    public static void getPurchaseLotto() {
        System.out.println("구입금액을 입력해 주세요.");
        sc.next();
    }

    public static Lotto getWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Lotto.of(sc.next());
    }

    public static Bonus getWinningBonus() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return new Bonus(sc.next());
    }
}
