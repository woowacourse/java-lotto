package lotto;

import java.util.ArrayList;
import java.util.Scanner;

public class LottoView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해주세요";

    public static String requestMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return SCANNER.nextLine();
    }

    public static void buyLotto(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        ArrayList<Lotto> lottoGroup = lottos.getLottos();
        for (int i = 0; i < lottos.getCount(); i++)
            System.out.print(lottoGroup.get(i).getLotto()+"\n");
    }

    public static String requestWinningNumber() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String requestBonusBallNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }
}
