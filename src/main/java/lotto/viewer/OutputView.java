package lotto.viewer;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoProfitRate;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Piece;
import lotto.exception.LottoException;

public class OutputView {

    private static final String REGULAR_RESULT_EXPRESSION = "%d개 일치, (%d원) - %d개";
    private static final String BONUS_RESULT_EXPRESSION = "5개 일치, 보너스볼일치(%d원) - %d개";
    private static final String PROFIT_RATE_EXPRESSION = "총 수익률은 %.2f입니다.";
    private static final String PURCHASE_PIECE_EXPRESSION = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";

    public void printPurchasedLottos(Lottos lottos, Piece manualPieces) {
        List<Lotto> lottoBunch = lottos.getLottoBunch();
        System.out.printf(PURCHASE_PIECE_EXPRESSION + System.lineSeparator(),
            manualPieces.getPieceNumber(), lottoBunch.size() - manualPieces.getPieceNumber());
        for (Lotto lotto : lottoBunch) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public void printLottoStatistics(LottoResult lottoResult, LottoProfitRate lottoProfitRate) {
        EnumMap<LottoRank, Integer> statistics = lottoResult.getLottoResultStatistics();
        double profitRate = lottoProfitRate.getProfitRate();
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (LottoRank key : statistics.keySet()) {
            printSingleResult(key, statistics.get(key));
        }
        System.out.printf(PROFIT_RATE_EXPRESSION + System.lineSeparator(), profitRate);
    }

    private void printSingleResult(LottoRank key, int value) {
        if (key.equals(LottoRank.NONE)) {
            return;
        }
        if (key.equals(LottoRank.SECOND)) {
            System.out.printf(BONUS_RESULT_EXPRESSION + System.lineSeparator(),
                key.getPrizeMoney(), value);
            return;
        }
        System.out.printf(REGULAR_RESULT_EXPRESSION + System.lineSeparator(),
            (int) key.getMatchingCount(), key.getPrizeMoney(), value);
    }

    public void printLottoException(LottoException lottoException) {
        System.out.println(lottoException.getMessage());
    }
}