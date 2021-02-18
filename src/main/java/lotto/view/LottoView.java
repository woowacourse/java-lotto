package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.ArrayList;
import java.util.Scanner;

public class LottoView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해주세요";

    public static String requestMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return SCANNER.nextLine();
    }

    public static void buyLotto(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        ArrayList<Lotto> lottoGroup = lottos.getLottoGroup();
        for (int i = 0; i < lottos.getCount(); i++)
            System.out.print(lottoGroup.get(i).getLottoNumbers() + "\n");
    }

    public static String requestWinningNumber() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static String requestBonusBallNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return SCANNER.nextLine();
    }

    public static void displayResultMessage() {
        System.out.println("\n당첨 통계\n----------");
//        displayResult(rank);
    }

    public static void displayResult(Rank rank, int count) {
        System.out.println(rank + Integer.toString(count) + "개");
    }

    public static void displayEarningRate(String earningRate) {
        System.out.println("총 수익률은 " + earningRate + "입니다.");
    }
}
