package lotto.view;

import java.util.Map;

import lotto.domain.Counter;
import lotto.domain.Lotto;
import lotto.domain.LottoGames;
import lotto.domain.LottoResult;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinLotto;

public class OutputView {
    private static final String START = "구매금액을 입력해주세요.";
    private static final String PURCHASED_GAME = "개 구매했습니다.";
    private static final String OPEN_SQUARE_BUCKET = "[";
    private static final String CLOSE_SQUARE_BUCKET = "]";
    private static final String INPUT_WIN = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String WIN_TITLE = "\n당첨 통계";
    private static final String WIN_CONTOUR = "---------";
    private static final String RANK_RESULT_FORMAT = "%d개 일치 (%d원) - %s개";

    static void start() {
        System.out.println(START);
    }

    public static void lottoList(LottoGames lottoGames) {
        System.out.println(lottoGames.size() + PURCHASED_GAME);
        for (Lotto lotto : lottoGames) {
            System.out.println(OPEN_SQUARE_BUCKET + lotto.getLotto() + CLOSE_SQUARE_BUCKET);
        }
        System.out.println();
    }

    static void win() {
        System.out.println(INPUT_WIN);
    }

    public static void winList(LottoGames lottoGames, WinLotto winLotto) {
        System.out.println(WIN_TITLE);
        System.out.println(WIN_CONTOUR);
        Map<Rank, Counter> lottoResult = LottoResult.create(lottoGames, winLotto);
        for (Rank rank : Rank.values()) {
            rankResult(lottoResult, rank);
        }
    }

    private static void rankResult(Map<Rank, Counter> lottoResult, Rank rank) {
        if (!rank.equals(Rank.MISS)) {
            System.out.println(String.format(RANK_RESULT_FORMAT, rank.getMatchCount(), rank.getPrize(), lottoResult.get(rank)));
        }
    }

    public static void rateOfReturn(PurchaseAmount purchaseAmount) {
        System.out.println("총 수익률은 " + LottoResult.getRateOfReturn(purchaseAmount) + "%입니다.");
    }
}
