package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoGames;

public class OutputView {
    public static void start() {
        System.out.println("구매금액을 입력해주세요.");
    }

    public static void lottoList(LottoGames lottoGames) {
        System.out.println(lottoGames.size() + "개 구매했습니다.");
        for (Lotto lotto : lottoGames) {
            System.out.println("[" + lotto.getLotto() + "]");
        }
        System.out.println();
    }

    public static void win() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public static void winList(LottoGames lottoGames) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
    }
}
