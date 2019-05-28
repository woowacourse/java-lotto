package lotto.view;

import java.util.Map;

import lotto.domain.Counter;
import lotto.domain.Lotto;
import lotto.domain.LottoGames;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinLotto;

//import static lotto.domain.Rank.*;

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

    public static void winList(LottoGames lottoGames, WinLotto winLotto) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        Map<Rank, Counter> lottoResult = LottoResult.create(lottoGames, winLotto);
        for (Rank rank : Rank.values()) {
            rankResult(lottoResult, rank);
        }
    }

    private static void rankResult(Map<Rank, Counter> lottoResult, Rank rank) {
        if (!rank.equals(Rank.MISS)) {
            System.out.println(rank.getMatchCount() + "개 일치 (" + rank.getPrize() + "원) - " + lottoResult.get(rank) + "개");
        }
    }
}
