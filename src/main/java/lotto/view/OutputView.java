package lotto.view;

import java.util.Map;

import lotto.domain.game.TotalLottoGames;
import lotto.domain.game.ManualCount;
import lotto.domain.game.ResultCounter;
import lotto.domain.lotto.Lotto;
import lotto.domain.game.LottoResult;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class OutputView {
    private static final String SECOND_ADDITIONAL = ", 보너스볼 일치";
    private static final String EMPTY_STRING = "";
    private static final String LIST_MESSAGE_FOOTER = "장을 구매하셨습니다.";
    private static final String MANUAL_HEADER = "수동으로 ";
    private static final String AUTO_HEADER = "자동으로 ";
    private static final String JOINER = "장, ";

    static void start() {
        System.out.println("구매금액을 입력해주세요.");
    }

    public static void lottoList(TotalLottoGames totalLottoGames) {
        System.out.println(getListMessage(totalLottoGames));
        for (Lotto lotto : totalLottoGames.allGames()) {
            System.out.println("[" + lotto.toString() + "]");
        }
        System.out.println();
    }

    private static String getListMessage(TotalLottoGames totalLottoGames) {
        if (!totalLottoGames.isManualEmpty() && totalLottoGames.isAutoEmpty()) {
            return MANUAL_HEADER + totalLottoGames.manualSize() + LIST_MESSAGE_FOOTER;
        }
        if (totalLottoGames.isManualEmpty() && !totalLottoGames.isAutoEmpty()) {
            return AUTO_HEADER + totalLottoGames.autoSize() + LIST_MESSAGE_FOOTER;
        }
        return MANUAL_HEADER + totalLottoGames.manualSize() + JOINER
                + AUTO_HEADER + totalLottoGames.autoSize() + LIST_MESSAGE_FOOTER;
    }

    static void win() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    static void bonus() {
        System.out.println("보너스 볼을 입력해주세요.");
    }

    public static void winList(TotalLottoGames totalLottoGames, WinningLotto winningLotto) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        Map<Rank, ResultCounter> lottoResult = LottoResult.create(totalLottoGames, winningLotto);
        for (Rank rank : Rank.values()) {
            rankResult(lottoResult, rank);
        }
    }

    private static void rankResult(Map<Rank, ResultCounter> lottoResult, Rank rank) {
        if (!rank.equals(Rank.MISS)) {
            System.out.println(getRankResult(lottoResult, rank));
        }
    }

    public static String getRankResult(Map<Rank, ResultCounter> lottoResult, Rank rank) {
        return String.format("%d개 일치%s (%d원) - %s개",
                rank.getMatchCount(), rankAdditional(rank), rank.getPrize(), lottoResult.get(rank));
    }

    private static String rankAdditional(Rank rank) {
        return rank.equals(Rank.SECOND) ? SECOND_ADDITIONAL : EMPTY_STRING;
    }

    public static void rateOfReturn(PurchaseAmount purchaseAmount) {
        System.out.println("총 수익률은 "
                + Math.round(LottoResult.rateOfReturn(purchaseAmount)) + "%입니다.");
    }

    static void manualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
    }

    public static void manualNumbers(ManualCount manualCount) {
        if (!manualCount.isZero()) {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        }
    }

    public static void indicator(int i) {
        System.out.print(i + " - ");
    }
}
