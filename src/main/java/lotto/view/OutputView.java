package lotto.view;

import java.util.Map;

import lotto.domain.Counter;
import lotto.domain.Lotto;
import lotto.domain.LottoGames;
import lotto.domain.LottoResult;
import lotto.domain.PurchaseAmount;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class OutputView {
    private static final String INPUT_PURCHASE_AMOUNT = "구매금액을 입력해주세요.";
    private static final String PURCHASED_GAME = "개 구매했습니다.";
    private static final String OPEN_SQUARE_BUCKET = "[";
    private static final String CLOSE_SQUARE_BUCKET = "]";
    private static final String INPUT_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS = "보너스 볼을 입력해주세요.";
    private static final String WIN_TITLE = "\n당첨 통계";
    private static final String WIN_CONTOUR = "---------";
    private static final String RANK_RESULT_FORMAT = "%d개 일치%s (%d원) - %s개";
    private static final String RETURN_HEADER = "총 수익률은 ";
    private static final String RETURN_FOOTER = "%입니다.";
    private static final String SECOND_ADDITIONAL = ", 보너스볼 일치";
    private static final String NO_ADDITIONAL = "";

    static void start() {
        System.out.println(INPUT_PURCHASE_AMOUNT);
    }

    public static void lottoList(LottoGames lottoGames) {
        System.out.println(lottoGames.size() + PURCHASED_GAME);
        for (Lotto lotto : lottoGames) {
            System.out.println(OPEN_SQUARE_BUCKET + lotto.getLotto() + CLOSE_SQUARE_BUCKET);
        }
        System.out.println();
    }

    static void win() {
        System.out.println(INPUT_WINNING_NUMBERS);
    }

    static void bonus() {
        System.out.println(INPUT_BONUS);
    }

    public static void winList(LottoGames lottoGames, WinningLotto winningLotto) {
        System.out.println(WIN_TITLE);
        System.out.println(WIN_CONTOUR);
        Map<Rank, Counter> lottoResult = LottoResult.create(lottoGames, winningLotto);
        for (Rank rank : Rank.values()) {
            rankResult(lottoResult, rank);
        }
    }

    private static void rankResult(Map<Rank, Counter> lottoResult, Rank rank) {
        if (!rank.equals(Rank.MISS)) {
            System.out.println(getRankResult(lottoResult, rank));
        }
    }

    private static String getRankResult(Map<Rank, Counter> lottoResult, Rank rank) {
        return String.format(RANK_RESULT_FORMAT, rank.getMatchCount(), rankAdditional(rank), rank.getPrize(), lottoResult.get(rank));
    }

    private static String rankAdditional(Rank rank) {
        return rank.equals(Rank.SECOND) ? SECOND_ADDITIONAL : NO_ADDITIONAL;
    }

    public static void rateOfReturn(PurchaseAmount purchaseAmount) {
        System.out.println(RETURN_HEADER + Math.round(LottoResult.getRateOfReturn(purchaseAmount)) + RETURN_FOOTER);
    }
}
