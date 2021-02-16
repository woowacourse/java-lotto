package lotto;

import java.util.ArrayList;
import java.util.Scanner;

public class LottoView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String requestMoney() {
        System.out.println("구입금액을 입력해주세요");
        return SCANNER.nextLine();
    }

    public static void buyLotto(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        ArrayList<ArrayList<Integer>> lotto = lottos.getLottos();
        for (int i = 0; i < lottos.getCount(); i++) {
            System.out.print("[");
            for (int j = 0; j < 5; j++) {
                System.out.print(lotto.get(i).get(j)+", ");
            }
            System.out.print(lotto.get(i).get(5));
            System.out.print("]\n");
        }
    }
}
